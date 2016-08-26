package io.bluecabin.common.macros

import scala.annotation.tailrec
import scala.reflect.macros.blackbox.Context

/**
  * Created by fergus on 3/10/16.
  */
private[macros] object MacrosImpl {

  def singletonsImpl[A: c.WeakTypeTag](c: Context): c.Expr[Traversable[A]] = {
    import c.universe._
    val A = weakTypeOf[A]
    val typeSym = A.typeSymbol
    val singletonIdents = if ((typeSym.isClass) && (typeSym.asClass.isSealed)) {

      @tailrec def topParentInPackage(s: c.universe.Symbol): c.universe.Symbol = {
        val owner = s.owner
        if (owner.isPackage) owner else if (owner == NoSymbol) s else topParentInPackage(owner)
      }

      @tailrec def nestedSingletons(inputs: Vector[c.universe.Symbol], currResult: Vector[c.universe.Symbol])
                                   (filter: c.universe.ModuleSymbol => Boolean): Vector[c.universe.Symbol] = {
        inputs.headOption match {
          case Some(m: ModuleSymbol) =>
            val newResult = if (filter(m)) currResult :+ m else currResult
            nestedSingletons(m.typeSignature.decls.toVector ++ inputs.tail, newResult)(filter)
          case Some(m: ClassSymbol) => nestedSingletons(m.typeSignature.decls.toVector ++ inputs.tail, currResult)(filter)
          case Some(s) =>
            nestedSingletons(inputs.tail, currResult)(filter)
          case None => currResult
        }
      }

      val top = topParentInPackage(c.internal.enclosingOwner)
      nestedSingletons(Vector(top), Vector.empty) { m =>
        m.typeSignature.baseClasses.contains(typeSym)
      } map (Ident(_))

    } else {
      c.abort(
        c.enclosingPosition,
        "Only sealed traits or classes are allowed"
      )
    }
    c.Expr[Traversable[A]](q"Traversable(..$singletonIdents)")
  }

}