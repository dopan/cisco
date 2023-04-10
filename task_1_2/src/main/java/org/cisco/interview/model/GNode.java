package org.cisco.interview.model;

import java.util.List;

public interface GNode {
    public String getName();

    public List<GNode> getChildren();
}