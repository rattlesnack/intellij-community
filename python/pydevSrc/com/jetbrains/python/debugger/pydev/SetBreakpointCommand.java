package com.jetbrains.python.debugger.pydev;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SetBreakpointCommand extends LineBreakpointCommand {
  private @Nullable final String myCondition;
  private @Nullable final String myLogExpression;
  private @Nullable final String myFuncName;

  public SetBreakpointCommand(@NotNull final RemoteDebugger debugger,
                              @NotNull final String type,
                              @NotNull final String file,
                              @NotNull final int line) {
    this(debugger, type, file, line, null, null, null);
  }


  public SetBreakpointCommand(@NotNull final RemoteDebugger debugger,
                              @NotNull final String type,
                              @NotNull final String file,
                              @NotNull final int line,
                              @Nullable final String condition,
                              @Nullable final String logExpression) {
    super(debugger, type, SET_BREAKPOINT, file, line);
    myCondition = condition;
    myLogExpression = logExpression;
    myFuncName = null;
  }

  public SetBreakpointCommand(@NotNull final RemoteDebugger debugger,
                              @NotNull final String type,
                              @NotNull final String file,
                              @NotNull final int line,
                              @Nullable final String condition,
                              @Nullable final String logExpression,
                              @Nullable final String funcName) {
    super(debugger, type, SET_BREAKPOINT, file, line);
    myCondition = condition;
    myLogExpression = logExpression;
    myFuncName = funcName;
  }

  @Override
  protected void buildPayload(Payload payload) {
    super.buildPayload(payload);
    payload.add(buildConditionWithFunc(myCondition, myFuncName)).add(buildCondition(myLogExpression));
  }

  @NotNull
  private static String buildConditionWithFunc(String expression, String functionName) {
    StringBuilder builder = new StringBuilder();

    if (functionName != null) {
      builder.append(FUNC_IN_CONDITION);
      builder.append(functionName);
      builder.append(TAB_CHAR);
    }

    if (expression != null) {
      builder.append(expression.replaceAll("\n", NEW_LINE_CHAR));
    } else {
      builder.append("None");
    }
    String condition = builder.toString();

    return condition.replaceAll("\t", TAB_CHAR);
  }

  @NotNull
  private static String buildCondition(String expression) {
    String condition;

    if (expression != null) {
      condition = expression.replaceAll("\n", NEW_LINE_CHAR);
      condition = condition.replaceAll("\t", TAB_CHAR);
    }
    else {
      condition = "None";
    }
    return condition;
  }
}
