package com.bju.cps450.application;

import com.bju.cps450.node.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by daniel on 3/6/16.
 */
public class Application {
    public static class NodePropertiesContainer{
        private Type type;
       // private InstructionSet code;

        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }
    }

    private static Map<Node, NodePropertiesContainer> nodePropertyMap = new HashMap<>();

    public static NodePropertiesContainer getNodeProperties(Node node){
        if(nodePropertyMap.get(node) == null){
            nodePropertyMap.put(node, new NodePropertiesContainer());
        }
        return nodePropertyMap.get(node);
    }


    

}
