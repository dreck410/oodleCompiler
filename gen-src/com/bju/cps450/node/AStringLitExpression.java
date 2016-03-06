/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.bju.cps450.node;

import com.bju.cps450.analysis.*;

@SuppressWarnings("nls")
public final class AStringLitExpression extends PExpression
{
    private TStringLit _stringLit_;

    public AStringLitExpression()
    {
        // Constructor
    }

    public AStringLitExpression(
        @SuppressWarnings("hiding") TStringLit _stringLit_)
    {
        // Constructor
        setStringLit(_stringLit_);

    }

    @Override
    public Object clone()
    {
        return new AStringLitExpression(
            cloneNode(this._stringLit_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAStringLitExpression(this);
    }

    public TStringLit getStringLit()
    {
        return this._stringLit_;
    }

    public void setStringLit(TStringLit node)
    {
        if(this._stringLit_ != null)
        {
            this._stringLit_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._stringLit_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._stringLit_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._stringLit_ == child)
        {
            this._stringLit_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._stringLit_ == oldChild)
        {
            setStringLit((TStringLit) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}