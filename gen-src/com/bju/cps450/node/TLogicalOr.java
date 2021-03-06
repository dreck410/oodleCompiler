/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.bju.cps450.node;

import com.bju.cps450.analysis.*;

@SuppressWarnings("nls")
public final class TLogicalOr extends Token
{
    public TLogicalOr()
    {
        super.setText("or");
    }

    public TLogicalOr(int line, int pos)
    {
        super.setText("or");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TLogicalOr(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTLogicalOr(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TLogicalOr text.");
    }
}
