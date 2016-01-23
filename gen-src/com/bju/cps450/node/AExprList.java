/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.bju.cps450.node;

import java.util.*;
import com.bju.cps450.analysis.*;

@SuppressWarnings("nls")
public final class AExprList extends PExprList
{
    private PExpression _expression_;
    private final LinkedList<PCommaExpr> _commaExpr_ = new LinkedList<PCommaExpr>();

    public AExprList()
    {
        // Constructor
    }

    public AExprList(
        @SuppressWarnings("hiding") PExpression _expression_,
        @SuppressWarnings("hiding") List<?> _commaExpr_)
    {
        // Constructor
        setExpression(_expression_);

        setCommaExpr(_commaExpr_);

    }

    @Override
    public Object clone()
    {
        return new AExprList(
            cloneNode(this._expression_),
            cloneList(this._commaExpr_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAExprList(this);
    }

    public PExpression getExpression()
    {
        return this._expression_;
    }

    public void setExpression(PExpression node)
    {
        if(this._expression_ != null)
        {
            this._expression_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._expression_ = node;
    }

    public LinkedList<PCommaExpr> getCommaExpr()
    {
        return this._commaExpr_;
    }

    public void setCommaExpr(List<?> list)
    {
        for(PCommaExpr e : this._commaExpr_)
        {
            e.parent(null);
        }
        this._commaExpr_.clear();

        for(Object obj_e : list)
        {
            PCommaExpr e = (PCommaExpr) obj_e;
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
            this._commaExpr_.add(e);
        }
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._expression_)
            + toString(this._commaExpr_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._expression_ == child)
        {
            this._expression_ = null;
            return;
        }

        if(this._commaExpr_.remove(child))
        {
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._expression_ == oldChild)
        {
            setExpression((PExpression) newChild);
            return;
        }

        for(ListIterator<PCommaExpr> i = this._commaExpr_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PCommaExpr) newChild);
                    newChild.parent(this);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

        throw new RuntimeException("Not a child.");
    }
}
