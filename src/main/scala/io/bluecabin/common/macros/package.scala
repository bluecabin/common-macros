package io.bluecabin.common


/**
  * Created by fergus on 8/26/16.
  */
package object macros {

  import scala.language.experimental.macros

  /**
    * Returns all singletons (object) instances of sealed type A in the current package
    *
    * @tparam A
    * @return
    */
  def singletons[A]: Traversable[A] = macro MacrosImpl.singletonsImpl[A]

}
