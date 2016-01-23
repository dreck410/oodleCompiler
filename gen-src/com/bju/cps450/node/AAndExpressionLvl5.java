/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.bju.cps450.node;

import com.bju.cps450.analysis.*;

@SuppressWarnings("nls")
public final class AAndExpressionLvl5 extends PExpressionLvl5
{
    private PExpressionLvl5 _expressionLvl5_;
    private TLogicalAnd _logicalAnd_;
    private PExpressionLvl4 _expressionLvl4_;

    public AAndExpressionLvl5()
    {
        // Constructor
    }

    public AAndExpressionLvl5(
        @SuppressWarnings("hiding") PExpressionLvl5 _expressionLvl5_,
        @SuppressWarnings("hiding") TLogicalAnd _logicalAnd_,
        @SuppressWarnings("hiding") PExpressionLvl4 _expressionLvl4_)
    {
        // Constructor
        setExpressionLvl5(_expressionLvl5_);

        setLogicalAnd(_logicalAnd_);

        setExpressionLvl4(_expressionLvl4_);

    }

    @Override
    public Object clone()
    {
        return new AAndExpressionLvl5(
            cloneNode(this._expressionLvl5_),
            cloneNode(this._logicalAnd_),
            cloneNode(this._expressionLvl4_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAAndExpressionLvl5(this);
    }

    public PExpressionLvl5 getExpressionLvl5()
    {
        return this._expressionLvl5_;
    }

    public void setExpressionLvl5(PExpressionLvl5 node)
    {
        if(this._expressionLvl5_ != null)
        {
            this._expressionLvl5_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._expressionLvl5_ = node;
    }

    public TLogicalAnd getLogicalAnd()
    {
        return this._logicalAnd_;
    }

    public void setLogicalAnd(TLogicalAnd node)
    {
        if(this._logicalAnd_ != null)
        {
            this._logicalAnd_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._logicalAnd_ = node;
    }

    public PExpressionLvl4 getExpressionLvl4()
    {
        return this._expressionLvl4_;
    }

    public void setExpressionLvl4(PExpressionLvl4 node)
    {
        if(this._expressionLvl4_ != null)
        {
            this._expressionLvl4_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._expressionLvl4_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._expressionLvl5_)
            + toString(this._logicalAnd_)
            + toString(this._expressionLvl4_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._expressionLvl5_ == child)
        {
            this._expressionLvl5_ = null;
            return;
        }

        if(this._logicalAnd_ == child)
        {
            this._logicalAnd_ = null;
            return;
        }

        if(this._expressionLvl4_ == child)
        {
            this._expressionLvl4_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._expressionLvl5_ == oldChild)
        {
            setExpressionLvl5((PExpressionLvl5) newChild);
            return;
        }

        if(this._logicalAnd_ == oldChild)
        {
            setLogicalAnd((TLogicalAnd) newChild);
            return;
        }

        if(this._expressionLvl4_ == oldChild)
        {
            setExpressionLvl4((PExpressionLvl4) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
