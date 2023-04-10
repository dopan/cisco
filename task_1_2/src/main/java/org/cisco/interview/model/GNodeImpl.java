package org.cisco.interview.model;

import java.util.List;
import java.util.Objects;

public class GNodeImpl implements GNode {

    private final String name;

    private final List<GNode> children;

    public GNodeImpl(String name, List<GNode> children) {
        if (name == null || children == null)
            throw new IllegalArgumentException("Name and children must not be null.");
        this.name = name;
        this.children = children;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<GNode> getChildren() {
        return children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GNodeImpl gNode = (GNodeImpl) o;
        return Objects.equals(getName(), gNode.getName()) && Objects.equals(getChildren(), gNode.getChildren());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getChildren());
    }
}