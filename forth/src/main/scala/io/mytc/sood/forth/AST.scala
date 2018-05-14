package io.mytc.sood.forth

sealed trait Statement

object Statement {
  final case class Integ(v: Int)                              extends Statement
  final case class Float(v: Double)                           extends Statement
  final case class Ident(v: String)                           extends Statement
  final case class Delim(v: String)                           extends Statement
  final case class Dword(name: String, block: Seq[Statement]) extends Statement
}