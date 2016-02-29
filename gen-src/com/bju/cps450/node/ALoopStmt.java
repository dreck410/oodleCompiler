/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.bju.cps450.node;

import java.util.*;
import com.bju.cps450.analysis.*;

@SuppressWarnings("nls")
public final class ALoopStmt extends PLoopStmt
{
    private TLoop _loop_;
    private TWhile _while_;
    private PExpression _expression_;
    private final LinkedList<TNewLine> _newLine_ = new LinkedList<TNewLine>();
    private PStmtList _stmtList_;
    private TEnd _end_;
    private TLoop _endloop_;

    public ALoopStmt()
    {
        // Constructor
    }

    public ALoopStmt(
        @SuppressWarnings("hiding") TLoop _loop_,
        @SuppressWarnings("hiding") TWhile _while_,
        @SuppressWarnings("hiding") PExpression _expression_,
        @SuppressWarnings("hiding") List<?> _newLine_,
        @SuppressWarnings("hiding") PStmtList _stmtList_,
        @SuppressWarnings("hiding") TEnd _end_,
        @SuppressWarnings("hiding") TLoop _endloop_)
    {
        // Constructor
        setLoop(_loop_);

        setWhile(_while_);

        setExpression(_expression_);

        setNewLine(_newLine_);

        setStmtList(_stmtList_);

        setEnd(_end_);

        setEndloop(_endloop_);

    }

    @Override
    public Object clone()
    {
        return new ALoopStmt(
            cloneNode(this._loop_),
            cloneNode(this._while_),
            cloneNode(this._expression_),
            cloneList(this._newLine_),
            cloneNode(this._stmtList_),
            cloneNode(this._end_),
            cloneNode(this._endloop_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseALoopStmt(this);
    }

    public TLoop getLoop()
    {
        return this._loop_;
    }

    public void setLoop(TLoop node)
    {
        if(this._loop_ != null)
        {
            this._loop_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._loop_ = node;
    }

    public TWhile getWhile()
    {
        return this._while_;
    }

    public void setWhile(TWhile node)
    {
        if(this._while_ != null)
        {
            this._while_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._while_ = node;
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

    public PStmtList getStmtList()
    {
        return this._stmtList_;
    }

    public void setStmtList(PStmtList node)
    {
        if(this._stmtList_ != null)
        {
            this._stmtList_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._stmtList_ = node;
    }

    public TEnd getEnd()
    {
        return this._end_;
    }

    public void setEnd(TEnd node)
    {
        if(this._end_ != null)
        {
            this._end_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._end_ = node;
    }

    public TLoop getEndloop()
    {
        return this._endloop_;
    }

    public void setEndloop(TLoop node)
    {
        if(this._endloop_ != null)
        {
            this._endloop_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._endloop_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._loop_)
            + toString(this._while_)
            + toString(this._expression_)
            + toString(this._newLine_)
            + toString(this._stmtList_)
            + toString(this._end_)
            + toString(this._endloop_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._loop_ == child)
        {
            this._loop_ = null;
            return;
        }

        if(this._while_ == child)
        {
            this._while_ = null;
            return;
        }

        if(this._expression_ == child)
        {
            this._expression_ = null;
            return;
        }

        if(this._newLine_.remove(child))
        {
            return;
        }

        if(this._stmtList_ == child)
        {
            this._stmtList_ = null;
            return;
        }

        if(this._end_ == child)
        {
            this._end_ = null;
            return;
        }

        if(this._endloop_ == child)
        {
            this._endloop_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._loop_ == oldChild)
        {
            setLoop((TLoop) newChild);
            return;
        }

        if(this._while_ == oldChild)
        {
            setWhile((TWhile) newChild);
            return;
        }

        if(this._expression_ == oldChild)
        {
            setExpression((PExpression) newChild);
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

        if(this._stmtList_ == oldChild)
        {
            setStmtList((PStmtList) newChild);
            return;
        }

        if(this._end_ == oldChild)
        {
            setEnd((TEnd) newChild);
            return;
        }

        if(this._endloop_ == oldChild)
        {
            setEndloop((TLoop) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}