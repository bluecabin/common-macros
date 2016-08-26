package io.bluecabin.common.macros

import org.specs2.concurrent.ExecutionEnv

/**
  * Created by fergus on 3/10/16.
  */
class MacrosSpec(implicit ee: ExecutionEnv) extends org.specs2.mutable.Specification {

  import MacrosSpec._

  "singletons" >> {
    "trait" >> {
      val expectedSubTraitInstances = Seq(SealedSubTrait.SubTraitInner, SealedSubTrait1, SealedSubTrait2)
      val expectedInstances = Seq(SealedTrait.TraitInner, SealedTrait1, SealedTrait2) ++ expectedSubTraitInstances
      SealedTrait.sealedTraitInstancesInner must containTheSameElementsAs(expectedInstances)
      sealedTraitInstancesOutter must containTheSameElementsAs(expectedInstances)
      SealedSubTrait.sealedSubTraitInstancesInner must containTheSameElementsAs(expectedSubTraitInstances)
      sealedSubTraitInstancesOutter must containTheSameElementsAs(expectedSubTraitInstances)
    }
    "abstract class" >> {
      val expectedSubClassInstances = Seq(SealedSubClass.SubClassInner, SealedSubClass1, SealedSubClass2)
      val expectedInstances = Seq(SealedClass.ClassInner, SealedClass1, SealedClass2) ++ expectedSubClassInstances
      SealedClass.sealedClassInstancesInner must containTheSameElementsAs(expectedInstances)
      sealedClassInstancesOutter must containTheSameElementsAs(expectedInstances)
      SealedSubClass.sealedSubClassInstancesInner must containTheSameElementsAs(expectedSubClassInstances)
      sealedSubClassInstancesOutter must containTheSameElementsAs(expectedSubClassInstances)
    }

  }
}

object MacrosSpec {
  val sealedTraitInstancesOutter = Macros.singletons[SealedTrait]
  val sealedSubTraitInstancesOutter = Macros.singletons[SealedSubTrait]
  val sealedClassInstancesOutter = Macros.singletons[SealedClass]
  val sealedSubClassInstancesOutter = Macros.singletons[SealedSubClass]

  sealed trait SealedTrait

  object SealedTrait {

    object TraitInner extends SealedTrait

    val sealedTraitInstancesInner = Macros.singletons[SealedTrait]
  }

  sealed trait SealedSubTrait extends SealedTrait

  object SealedSubTrait {

    object SubTraitInner extends SealedSubTrait

    val sealedSubTraitInstancesInner = Macros.singletons[SealedSubTrait]

  }

  object SealedTrait1 extends SealedTrait

  object SealedTrait2 extends SealedTrait

  val SealedTrait3 = new SealedTrait {}

  object SealedSubTrait1 extends SealedSubTrait

  object SealedSubTrait2 extends SealedSubTrait

  val SealedSubTrait3 = new SealedSubTrait {}


  sealed abstract class SealedClass

  object SealedClass {

    object ClassInner extends SealedClass

    val sealedClassInstancesInner = Macros.singletons[SealedClass]

  }

  sealed abstract class SealedSubClass extends SealedClass

  object SealedSubClass {

    object SubClassInner extends SealedSubClass

    val sealedSubClassInstancesInner = Macros.singletons[SealedSubClass]

  }

  object SealedClass1 extends SealedClass

  object SealedClass2 extends SealedClass

  val SealedClass3 = new SealedClass {}

  object SealedSubClass1 extends SealedSubClass

  object SealedSubClass2 extends SealedSubClass

  val SealedSubClass3 = new SealedSubClass {}
}