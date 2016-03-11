/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.bju.cps450.analysis;

import java.util.*;
import com.bju.cps450.node.*;

public class ReversedDepthFirstAdapter extends AnalysisAdapter
{
    public void inStart(Start node)
    {
        defaultIn(node);
    }

    public void outStart(Start node)
    {
        defaultOut(node);
    }

    public void defaultIn(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    public void defaultOut(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    @Override
    public void caseStart(Start node)
    {
        inStart(node);
        node.getEOF().apply(this);
        node.getPStart().apply(this);
        outStart(node);
    }

    public void inAStart(AStart node)
    {
        defaultIn(node);
    }

    public void outAStart(AStart node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAStart(AStart node)
    {
        inAStart(node);
        {
            List<PClassDecl> copy = new ArrayList<PClassDecl>(node.getClassDecl());
            Collections.reverse(copy);
            for(PClassDecl e : copy)
            {
                e.apply(this);
            }
        }
        outAStart(node);
    }

    public void inAClassDecl(AClassDecl node)
    {
        defaultIn(node);
    }

    public void outAClassDecl(AClassDecl node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAClassDecl(AClassDecl node)
    {
        inAClassDecl(node);
        if(node.getEnd() != null)
        {
            node.getEnd().apply(this);
        }
        {
            List<PMethodDecl> copy = new ArrayList<PMethodDecl>(node.getMethodDecl());
            Collections.reverse(copy);
            for(PMethodDecl e : copy)
            {
                e.apply(this);
            }
        }
        {
            List<PVarDecl> copy = new ArrayList<PVarDecl>(node.getVarDecl());
            Collections.reverse(copy);
            for(PVarDecl e : copy)
            {
                e.apply(this);
            }
        }
        if(node.getClassInherits() != null)
        {
            node.getClassInherits().apply(this);
        }
        if(node.getStart() != null)
        {
            node.getStart().apply(this);
        }
        outAClassDecl(node);
    }

    public void inAClassInherits(AClassInherits node)
    {
        defaultIn(node);
    }

    public void outAClassInherits(AClassInherits node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAClassInherits(AClassInherits node)
    {
        inAClassInherits(node);
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        outAClassInherits(node);
    }

    public void inAVarDecl(AVarDecl node)
    {
        defaultIn(node);
    }

    public void outAVarDecl(AVarDecl node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVarDecl(AVarDecl node)
    {
        inAVarDecl(node);
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        outAVarDecl(node);
    }

    public void inAMethodDecl(AMethodDecl node)
    {
        defaultIn(node);
    }

    public void outAMethodDecl(AMethodDecl node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMethodDecl(AMethodDecl node)
    {
        inAMethodDecl(node);
        if(node.getEnd() != null)
        {
            node.getEnd().apply(this);
        }
        {
            List<PStatement> copy = new ArrayList<PStatement>(node.getStatement());
            Collections.reverse(copy);
            for(PStatement e : copy)
            {
                e.apply(this);
            }
        }
        {
            List<PVarDecl> copy = new ArrayList<PVarDecl>(node.getVarDecl());
            Collections.reverse(copy);
            for(PVarDecl e : copy)
            {
                e.apply(this);
            }
        }
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        {
            List<PArg> copy = new ArrayList<PArg>(node.getArgs());
            Collections.reverse(copy);
            for(PArg e : copy)
            {
                e.apply(this);
            }
        }
        if(node.getName() != null)
        {
            node.getName().apply(this);
        }
        outAMethodDecl(node);
    }

    public void inAArg(AArg node)
    {
        defaultIn(node);
    }

    public void outAArg(AArg node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAArg(AArg node)
    {
        inAArg(node);
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        outAArg(node);
    }

    public void inAMethod(AMethod node)
    {
        defaultIn(node);
    }

    public void outAMethod(AMethod node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMethod(AMethod node)
    {
        inAMethod(node);
        if(node.getName() != null)
        {
            node.getName().apply(this);
        }
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        outAMethod(node);
    }

    public void inAIntType(AIntType node)
    {
        defaultIn(node);
    }

    public void outAIntType(AIntType node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIntType(AIntType node)
    {
        inAIntType(node);
        {
            List<PExpression> copy = new ArrayList<PExpression>(node.getExpression());
            Collections.reverse(copy);
            for(PExpression e : copy)
            {
                e.apply(this);
            }
        }
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        outAIntType(node);
    }

    public void inAStringType(AStringType node)
    {
        defaultIn(node);
    }

    public void outAStringType(AStringType node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAStringType(AStringType node)
    {
        inAStringType(node);
        {
            List<PExpression> copy = new ArrayList<PExpression>(node.getExpression());
            Collections.reverse(copy);
            for(PExpression e : copy)
            {
                e.apply(this);
            }
        }
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        outAStringType(node);
    }

    public void inABooleanType(ABooleanType node)
    {
        defaultIn(node);
    }

    public void outABooleanType(ABooleanType node)
    {
        defaultOut(node);
    }

    @Override
    public void caseABooleanType(ABooleanType node)
    {
        inABooleanType(node);
        {
            List<PExpression> copy = new ArrayList<PExpression>(node.getExpression());
            Collections.reverse(copy);
            for(PExpression e : copy)
            {
                e.apply(this);
            }
        }
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        outABooleanType(node);
    }

    public void inACustomType(ACustomType node)
    {
        defaultIn(node);
    }

    public void outACustomType(ACustomType node)
    {
        defaultOut(node);
    }

    @Override
    public void caseACustomType(ACustomType node)
    {
        inACustomType(node);
        {
            List<PExpression> copy = new ArrayList<PExpression>(node.getExpression());
            Collections.reverse(copy);
            for(PExpression e : copy)
            {
                e.apply(this);
            }
        }
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        outACustomType(node);
    }

    public void inAIfStatement(AIfStatement node)
    {
        defaultIn(node);
    }

    public void outAIfStatement(AIfStatement node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIfStatement(AIfStatement node)
    {
        inAIfStatement(node);
        {
            List<PStatement> copy = new ArrayList<PStatement>(node.getFalse());
            Collections.reverse(copy);
            for(PStatement e : copy)
            {
                e.apply(this);
            }
        }
        {
            List<PStatement> copy = new ArrayList<PStatement>(node.getTrue());
            Collections.reverse(copy);
            for(PStatement e : copy)
            {
                e.apply(this);
            }
        }
        if(node.getCondition() != null)
        {
            node.getCondition().apply(this);
        }
        outAIfStatement(node);
    }

    public void inALoopStatement(ALoopStatement node)
    {
        defaultIn(node);
    }

    public void outALoopStatement(ALoopStatement node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALoopStatement(ALoopStatement node)
    {
        inALoopStatement(node);
        {
            List<PStatement> copy = new ArrayList<PStatement>(node.getStatement());
            Collections.reverse(copy);
            for(PStatement e : copy)
            {
                e.apply(this);
            }
        }
        if(node.getCondition() != null)
        {
            node.getCondition().apply(this);
        }
        outALoopStatement(node);
    }

    public void inACallStatement(ACallStatement node)
    {
        defaultIn(node);
    }

    public void outACallStatement(ACallStatement node)
    {
        defaultOut(node);
    }

    @Override
    public void caseACallStatement(ACallStatement node)
    {
        inACallStatement(node);
        {
            List<PExpression> copy = new ArrayList<PExpression>(node.getArgs());
            Collections.reverse(copy);
            for(PExpression e : copy)
            {
                e.apply(this);
            }
        }
        if(node.getMethod() != null)
        {
            node.getMethod().apply(this);
        }
        if(node.getCaller() != null)
        {
            node.getCaller().apply(this);
        }
        outACallStatement(node);
    }

    public void inAAssignmentStatement(AAssignmentStatement node)
    {
        defaultIn(node);
    }

    public void outAAssignmentStatement(AAssignmentStatement node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAssignmentStatement(AAssignmentStatement node)
    {
        inAAssignmentStatement(node);
        if(node.getValue() != null)
        {
            node.getValue().apply(this);
        }
        if(node.getAssignee() != null)
        {
            node.getAssignee().apply(this);
        }
        outAAssignmentStatement(node);
    }

    public void inAAssignee(AAssignee node)
    {
        defaultIn(node);
    }

    public void outAAssignee(AAssignee node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAssignee(AAssignee node)
    {
        inAAssignee(node);
        {
            List<PExpression> copy = new ArrayList<PExpression>(node.getIndex());
            Collections.reverse(copy);
            for(PExpression e : copy)
            {
                e.apply(this);
            }
        }
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        outAAssignee(node);
    }

    public void inAOrExpression(AOrExpression node)
    {
        defaultIn(node);
    }

    public void outAOrExpression(AOrExpression node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAOrExpression(AOrExpression node)
    {
        inAOrExpression(node);
        if(node.getRhs() != null)
        {
            node.getRhs().apply(this);
        }
        if(node.getLhs() != null)
        {
            node.getLhs().apply(this);
        }
        outAOrExpression(node);
    }

    public void inAAndExpression(AAndExpression node)
    {
        defaultIn(node);
    }

    public void outAAndExpression(AAndExpression node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAndExpression(AAndExpression node)
    {
        inAAndExpression(node);
        if(node.getRhs() != null)
        {
            node.getRhs().apply(this);
        }
        if(node.getLhs() != null)
        {
            node.getLhs().apply(this);
        }
        outAAndExpression(node);
    }

    public void inAPlusExpression(APlusExpression node)
    {
        defaultIn(node);
    }

    public void outAPlusExpression(APlusExpression node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPlusExpression(APlusExpression node)
    {
        inAPlusExpression(node);
        if(node.getRhs() != null)
        {
            node.getRhs().apply(this);
        }
        if(node.getLhs() != null)
        {
            node.getLhs().apply(this);
        }
        outAPlusExpression(node);
    }

    public void inAMinusExpression(AMinusExpression node)
    {
        defaultIn(node);
    }

    public void outAMinusExpression(AMinusExpression node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMinusExpression(AMinusExpression node)
    {
        inAMinusExpression(node);
        if(node.getRhs() != null)
        {
            node.getRhs().apply(this);
        }
        if(node.getLhs() != null)
        {
            node.getLhs().apply(this);
        }
        outAMinusExpression(node);
    }

    public void inAEqExpression(AEqExpression node)
    {
        defaultIn(node);
    }

    public void outAEqExpression(AEqExpression node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEqExpression(AEqExpression node)
    {
        inAEqExpression(node);
        if(node.getRhs() != null)
        {
            node.getRhs().apply(this);
        }
        if(node.getLhs() != null)
        {
            node.getLhs().apply(this);
        }
        outAEqExpression(node);
    }

    public void inAGteqExpression(AGteqExpression node)
    {
        defaultIn(node);
    }

    public void outAGteqExpression(AGteqExpression node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAGteqExpression(AGteqExpression node)
    {
        inAGteqExpression(node);
        if(node.getRhs() != null)
        {
            node.getRhs().apply(this);
        }
        if(node.getLhs() != null)
        {
            node.getLhs().apply(this);
        }
        outAGteqExpression(node);
    }

    public void inAGtExpression(AGtExpression node)
    {
        defaultIn(node);
    }

    public void outAGtExpression(AGtExpression node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAGtExpression(AGtExpression node)
    {
        inAGtExpression(node);
        if(node.getRhs() != null)
        {
            node.getRhs().apply(this);
        }
        if(node.getLhs() != null)
        {
            node.getLhs().apply(this);
        }
        outAGtExpression(node);
    }

    public void inAMultiplyExpression(AMultiplyExpression node)
    {
        defaultIn(node);
    }

    public void outAMultiplyExpression(AMultiplyExpression node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMultiplyExpression(AMultiplyExpression node)
    {
        inAMultiplyExpression(node);
        if(node.getRhs() != null)
        {
            node.getRhs().apply(this);
        }
        if(node.getLhs() != null)
        {
            node.getLhs().apply(this);
        }
        outAMultiplyExpression(node);
    }

    public void inADivideExpression(ADivideExpression node)
    {
        defaultIn(node);
    }

    public void outADivideExpression(ADivideExpression node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADivideExpression(ADivideExpression node)
    {
        inADivideExpression(node);
        if(node.getRhs() != null)
        {
            node.getRhs().apply(this);
        }
        if(node.getLhs() != null)
        {
            node.getLhs().apply(this);
        }
        outADivideExpression(node);
    }

    public void inAConcatExpression(AConcatExpression node)
    {
        defaultIn(node);
    }

    public void outAConcatExpression(AConcatExpression node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAConcatExpression(AConcatExpression node)
    {
        inAConcatExpression(node);
        if(node.getRhs() != null)
        {
            node.getRhs().apply(this);
        }
        if(node.getLhs() != null)
        {
            node.getLhs().apply(this);
        }
        outAConcatExpression(node);
    }

    public void inANotExpression(ANotExpression node)
    {
        defaultIn(node);
    }

    public void outANotExpression(ANotExpression node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANotExpression(ANotExpression node)
    {
        inANotExpression(node);
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        outANotExpression(node);
    }

    public void inANegExpression(ANegExpression node)
    {
        defaultIn(node);
    }

    public void outANegExpression(ANegExpression node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANegExpression(ANegExpression node)
    {
        inANegExpression(node);
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        outANegExpression(node);
    }

    public void inAPosExpression(APosExpression node)
    {
        defaultIn(node);
    }

    public void outAPosExpression(APosExpression node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPosExpression(APosExpression node)
    {
        inAPosExpression(node);
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        outAPosExpression(node);
    }

    public void inANewExpression(ANewExpression node)
    {
        defaultIn(node);
    }

    public void outANewExpression(ANewExpression node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANewExpression(ANewExpression node)
    {
        inANewExpression(node);
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        outANewExpression(node);
    }

    public void inAMethodCallExpression(AMethodCallExpression node)
    {
        defaultIn(node);
    }

    public void outAMethodCallExpression(AMethodCallExpression node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMethodCallExpression(AMethodCallExpression node)
    {
        inAMethodCallExpression(node);
        {
            List<PExpression> copy = new ArrayList<PExpression>(node.getArgs());
            Collections.reverse(copy);
            for(PExpression e : copy)
            {
                e.apply(this);
            }
        }
        if(node.getMethod() != null)
        {
            node.getMethod().apply(this);
        }
        if(node.getCaller() != null)
        {
            node.getCaller().apply(this);
        }
        outAMethodCallExpression(node);
    }

    public void inAIntExpression(AIntExpression node)
    {
        defaultIn(node);
    }

    public void outAIntExpression(AIntExpression node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIntExpression(AIntExpression node)
    {
        inAIntExpression(node);
        if(node.getInteger() != null)
        {
            node.getInteger().apply(this);
        }
        outAIntExpression(node);
    }

    public void inAStringLitExpression(AStringLitExpression node)
    {
        defaultIn(node);
    }

    public void outAStringLitExpression(AStringLitExpression node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAStringLitExpression(AStringLitExpression node)
    {
        inAStringLitExpression(node);
        if(node.getStringLit() != null)
        {
            node.getStringLit().apply(this);
        }
        outAStringLitExpression(node);
    }

    public void inATrueExpression(ATrueExpression node)
    {
        defaultIn(node);
    }

    public void outATrueExpression(ATrueExpression node)
    {
        defaultOut(node);
    }

    @Override
    public void caseATrueExpression(ATrueExpression node)
    {
        inATrueExpression(node);
        if(node.getTrue() != null)
        {
            node.getTrue().apply(this);
        }
        outATrueExpression(node);
    }

    public void inAFalseExpression(AFalseExpression node)
    {
        defaultIn(node);
    }

    public void outAFalseExpression(AFalseExpression node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFalseExpression(AFalseExpression node)
    {
        inAFalseExpression(node);
        if(node.getFalse() != null)
        {
            node.getFalse().apply(this);
        }
        outAFalseExpression(node);
    }

    public void inANullExpression(ANullExpression node)
    {
        defaultIn(node);
    }

    public void outANullExpression(ANullExpression node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANullExpression(ANullExpression node)
    {
        inANullExpression(node);
        if(node.getNull() != null)
        {
            node.getNull().apply(this);
        }
        outANullExpression(node);
    }

    public void inAMeExpression(AMeExpression node)
    {
        defaultIn(node);
    }

    public void outAMeExpression(AMeExpression node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMeExpression(AMeExpression node)
    {
        inAMeExpression(node);
        if(node.getMe() != null)
        {
            node.getMe().apply(this);
        }
        outAMeExpression(node);
    }

    public void inAArrayExpression(AArrayExpression node)
    {
        defaultIn(node);
    }

    public void outAArrayExpression(AArrayExpression node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAArrayExpression(AArrayExpression node)
    {
        inAArrayExpression(node);
        {
            List<PExpression> copy = new ArrayList<PExpression>(node.getExpression());
            Collections.reverse(copy);
            for(PExpression e : copy)
            {
                e.apply(this);
            }
        }
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        outAArrayExpression(node);
    }

    public void inAIdExpression(AIdExpression node)
    {
        defaultIn(node);
    }

    public void outAIdExpression(AIdExpression node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIdExpression(AIdExpression node)
    {
        inAIdExpression(node);
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        outAIdExpression(node);
    }
}
