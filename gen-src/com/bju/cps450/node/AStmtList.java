/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.bju.cps450.node;

import java.util.*;
import com.bju.cps450.analysis.*;

@SuppressWarnings("nls")
public final class AStmtList extends PStmtList
{
    private final LinkedList<PStmt> _stmt_ = new LinkedList<PStmt>();

    public AStmtList()
    {
        // Constructor
    }

    public AStmtList(
        @SuppressWarnings("hiding") List<?> _stmt_)
    {
        // Constructor
        setStmt(_stmt_);

    }

    @Override
    public Object clone()
    {
        return new AStmtList(
            cloneList(this._stmt_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAStmtList(this);
    }

    public LinkedList<PStmt> getStmt()
    {
        return this._stmt_;
    }

    public void setStmt(List<?> list)
    {
        for(PStmt e : this._stmt_)
        {
            e.parent(null);
        }
        this._stmt_.clear();

        for(Object obj_e : list)
        {
            PStmt e = (PStmt) obj_e;
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
            this._stmt_.add(e);
        }
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._stmt_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._stmt_.remove(child))
        {
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        for(ListIterator<PStmt> i = this._stmt_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PStmt) newChild);
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
