/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.bju.cps450.node;

import com.bju.cps450.analysis.*;

@SuppressWarnings("nls")
public final class AClassDecl extends PClassDecl
{
    private PClassHead _classHead_;
    private PClassBody _classBody_;
    private PEnd _end_;

    public AClassDecl()
    {
        // Constructor
    }

    public AClassDecl(
        @SuppressWarnings("hiding") PClassHead _classHead_,
        @SuppressWarnings("hiding") PClassBody _classBody_,
        @SuppressWarnings("hiding") PEnd _end_)
    {
        // Constructor
        setClassHead(_classHead_);

        setClassBody(_classBody_);

        setEnd(_end_);

    }

    @Override
    public Object clone()
    {
        return new AClassDecl(
            cloneNode(this._classHead_),
            cloneNode(this._classBody_),
            cloneNode(this._end_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAClassDecl(this);
    }

    public PClassHead getClassHead()
    {
        return this._classHead_;
    }

    public void setClassHead(PClassHead node)
    {
        if(this._classHead_ != null)
        {
            this._classHead_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._classHead_ = node;
    }

    public PClassBody getClassBody()
    {
        return this._classBody_;
    }

    public void setClassBody(PClassBody node)
    {
        if(this._classBody_ != null)
        {
            this._classBody_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._classBody_ = node;
    }

    public PEnd getEnd()
    {
        return this._end_;
    }

    public void setEnd(PEnd node)
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

    @Override
    public String toString()
    {
        return ""
            + toString(this._classHead_)
            + toString(this._classBody_)
            + toString(this._end_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._classHead_ == child)
        {
            this._classHead_ = null;
            return;
        }

        if(this._classBody_ == child)
        {
            this._classBody_ = null;
            return;
        }

        if(this._end_ == child)
        {
            this._end_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._classHead_ == oldChild)
        {
            setClassHead((PClassHead) newChild);
            return;
        }

        if(this._classBody_ == oldChild)
        {
            setClassBody((PClassBody) newChild);
            return;
        }

        if(this._end_ == oldChild)
        {
            setEnd((PEnd) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
