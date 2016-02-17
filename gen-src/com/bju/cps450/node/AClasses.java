/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.bju.cps450.node;

import com.bju.cps450.analysis.*;

@SuppressWarnings("nls")
public final class AClasses extends PClasses
{
    private PClassDecl _classDecl_;
    private PClasses _classes_;

    public AClasses()
    {
        // Constructor
    }

    public AClasses(
        @SuppressWarnings("hiding") PClassDecl _classDecl_,
        @SuppressWarnings("hiding") PClasses _classes_)
    {
        // Constructor
        setClassDecl(_classDecl_);

        setClasses(_classes_);

    }

    @Override
    public Object clone()
    {
        return new AClasses(
            cloneNode(this._classDecl_),
            cloneNode(this._classes_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAClasses(this);
    }

    public PClassDecl getClassDecl()
    {
        return this._classDecl_;
    }

    public void setClassDecl(PClassDecl node)
    {
        if(this._classDecl_ != null)
        {
            this._classDecl_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._classDecl_ = node;
    }

    public PClasses getClasses()
    {
        return this._classes_;
    }

    public void setClasses(PClasses node)
    {
        if(this._classes_ != null)
        {
            this._classes_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._classes_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._classDecl_)
            + toString(this._classes_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._classDecl_ == child)
        {
            this._classDecl_ = null;
            return;
        }

        if(this._classes_ == child)
        {
            this._classes_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._classDecl_ == oldChild)
        {
            setClassDecl((PClassDecl) newChild);
            return;
        }

        if(this._classes_ == oldChild)
        {
            setClasses((PClasses) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
