/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.bju.cps450.node;

import com.bju.cps450.analysis.*;

@SuppressWarnings("nls")
public final class TLogicalAnd extends Token
{
    public TLogicalAnd()
    {
        super.setText("and");
    }

    public TLogicalAnd(int line, int pos)
    {
        super.setText("and");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TLogicalAnd(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTLogicalAnd(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TLogicalAnd text.");
    }
}