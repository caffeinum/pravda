package pravda.dotnet.translation

import pravda.common.bytes.hex._
import pravda.dotnet.DiffUtils
import pravda.dotnet.parsers.FileParser
import pravda.vm.asm.Datum._
import pravda.vm.asm.Op._
import utest._

object SmartProgramTests extends TestSuite {

  val tests = Tests {
    'smartProgramParse - {
      val Right((_, cilData, methods, signatures)) = FileParser.parseFile("smart_program.exe")

      DiffUtils.assertEqual(
        Translator.translateAsm(methods, cilData, signatures),
        Right(
          List(
            Dup,
            Push(Rawbytes("balanceOf".getBytes)),
            Eq,
            JumpI("method_balanceOf"),
            Dup,
            Push(Rawbytes("transfer".getBytes)),
            Eq,
            JumpI("method_transfer"),
            Dup,
            Push(Rawbytes("Main".getBytes)),
            Eq,
            JumpI("method_Main"),
            Jump("stop"),
            Label("method_balanceOf"),
            Push(Integral(0)),
            Nop,
            Push(Rawbytes("balances".getBytes)),
            Push(Integral(4)),
            Dupn,
            Push(Rawbytes(hex"01 00 00 00 00")),
            Call("method_getDefault"),
            Push(Integral(2)),
            SwapN,
            Pop,
            Nop,
            Push(Integral(1)),
            Dupn,
            Swap,
            Pop,
            Swap,
            Pop,
            Swap,
            Pop,
            Jump("stop"),
            Label("method_transfer"),
            Push(Integral(0)),
            Push(Integral(0)),
            Nop,
            Push(Integral(4)),
            Dupn,
            Push(Rawbytes(hex"01 00 00 00 00")),
            Swap,
            LCall("Typed", "typedClt", 2),
            Push(Integral(3)),
            SwapN,
            Pop,
            Push(Integral(2)),
            Dupn,
            LCall("Typed", "typedNot", 1),
            Push(Rawbytes(hex"01 00 00 00 01")),
            Eq,
            JumpI("br107"),
            Nop,
            Push(Rawbytes("balances".getBytes)),
            From,
            Push(Rawbytes(hex"01 00 00 00 00")),
            Call("method_getDefault"),
            Push(Integral(5)),
            Dupn,
            LCall("Typed", "typedClt", 2),
            Push(Rawbytes(hex"01 00 00 00 00")),
            Eq,
            LCall("Typed", "typedBool", 1),
            Push(Integral(2)),
            SwapN,
            Pop,
            Push(Integral(1)),
            Dupn,
            LCall("Typed", "typedNot", 1),
            Push(Rawbytes(hex"01 00 00 00 01")),
            Eq,
            JumpI("br106"),
            Nop,
            Push(Rawbytes("balances".getBytes)),
            From,
            Push(Rawbytes("balances".getBytes)),
            From,
            Push(Rawbytes(hex"01 00 00 00 00")),
            Call("method_getDefault"),
            Push(Integral(7)),
            Dupn,
            Push(Rawbytes(hex"01 ff ff ff ff")),
            LCall("Typed", "typedMul", 2),
            LCall("Typed", "typedAdd", 2),
            Push(Integral(2)),
            Dupn,
            Push(Integral(4)),
            Dupn,
            Concat,
            Swap,
            SPut,
            Pop,
            Pop,
            Nop,
            Push(Rawbytes("balances".getBytes)),
            Push(Integral(6)),
            Dupn,
            Push(Rawbytes("balances".getBytes)),
            Push(Integral(8)),
            Dupn,
            Push(Rawbytes(hex"01 00 00 00 00")),
            Call("method_getDefault"),
            Push(Integral(7)),
            Dupn,
            LCall("Typed", "typedAdd", 2),
            Push(Integral(2)),
            Dupn,
            Push(Integral(4)),
            Dupn,
            Concat,
            Swap,
            SPut,
            Pop,
            Pop,
            Nop,
            Nop,
            Label("br106"),
            Nop,
            Label("br107"),
            Pop,
            Pop,
            Pop,
            Pop,
            Pop,
            Jump("stop"),
            Label("method_Main"),
            Nop,
            Pop,
            Jump("stop"),
            Label("method_get"),
            Swap,
            Pop,
            Swap,
            Pop,
            Ret,
            Label("method_exists"),
            Swap,
            Pop,
            Swap,
            Pop,
            Ret,
            Label("method_put"),
            Pop,
            Pop,
            Pop,
            Ret,
            Label("method_getDefault"),
            Push(Integral(0)),
            Push(Integral(0)),
            Nop,
            Push(Integral(5)),
            Dupn,
            Push(Integral(5)),
            Dupn,
            Swap,
            Concat,
            SExst,
            LCall("Typed", "typedBool", 1),
            Push(Rawbytes(hex"01 00 00 00 00")),
            Eq,
            LCall("Typed", "typedBool", 1),
            Push(Integral(3)),
            SwapN,
            Pop,
            Push(Integral(2)),
            Dupn,
            LCall("Typed", "typedNot", 1),
            Push(Rawbytes(hex"01 00 00 00 01")),
            Eq,
            JumpI("br20"),
            Nop,
            Push(Integral(3)),
            Dupn,
            Push(Integral(2)),
            SwapN,
            Pop,
            Jump("br31"),
            Label("br20"),
            Nop,
            Push(Integral(5)),
            Dupn,
            Push(Integral(5)),
            Dupn,
            Swap,
            Concat,
            SGet,
            Push(Integral(2)),
            SwapN,
            Pop,
            Nop,
            Label("br31"),
            Push(Integral(1)),
            Dupn,
            Swap,
            Pop,
            Swap,
            Pop,
            Swap,
            Pop,
            Swap,
            Pop,
            Swap,
            Pop,
            Ret,
            Label("stop")
          ))
      )
    }
  }
}
