/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.bju.cps450.node;

import java.util.*;
import com.bju.cps450.analysis.*;

@SuppressWarnings("nls")
public final class ACallStmtStmt extends PStmt
{
    private PCallStmt _callStmt_;
    private final LinkedList<TNewLine> _newLine_ = new LinkedList<TNewLine>();

    public ACallStmtStmt()
    {
        // Constructor
    }

    public ACallStmtStmt(
        @SuppressWarnings("hiding") PCallStmt _callStmt_,
        @SuppressWarnings("hiding") List<?> _newLine_)
    {
        // Constructor
        setCallStmt(_callStmt_);

        setNewLine(_newLine_);

    }

    @Override
    public Object clone()
    {
        return new ACallStmtStmt(
            cloneNode(this._callStmt_),
            cloneList(this._newLine_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseACallStmtStmt(this);
    }

    public PCallStmt getCallStmt()
    {
        return this._callStmt_;
    }

    public void setCallStmt(PCallStmt node)
    {
        if(this._callStmt_ != null)
        {
            this._callStmt_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._callStmt_ = node;
    }

    public LinkedList<TNewLine> getNewLine()
    {
        return this._newLine_;
    }

    public void setNewLine(List<?> list)
    {
        for(TNewLine e : this._newLine_)
        {
            e.parent(null);
        }
        this._newLine_.clear();

        for(Object obj_e : list)
        {
            TNewLine e = (TNewLine) obj_e;
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
            this._newLine_.add(e);
        }
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._callStmt_)
            + toString(this._newLine_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._callStmt_ == child)
        {
            this._callStmt_ = null;
            return;
        }

        if(this._newLine_.remove(child))
        {
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._callStmt_ == oldChild)
        {
            setCallStmt((PCallStmt) newChild);
            return;
        }

        for(ListIterator<TNewLine> i = this._newLine_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((TNewLine) newChild);
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