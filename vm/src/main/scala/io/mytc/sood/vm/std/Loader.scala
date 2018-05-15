package io.mytc.sood.vm
package std

import state.{WorldState, Address}

object Loader extends Loader {

  val libraries: Seq[Lib] = Array(
    libs.Math,
    libs.Typed
  )

  private lazy val libsTable = libraries.map(l => l.address -> l).toMap

  override def lib(address: Address, worldState: WorldState): Option[Lib] =
    libsTable.get(address.toStringUtf8)

}
