package com.eruption.engine.core.graphics;

import com.eruption.engine.core.math.Color4f;
import com.eruption.engine.core.math.Vector2f;
import com.eruption.engine.core.math.Vector3f;
import com.eruption.engine.core.math.Vector4f;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mesh {

    private EPrimitive primitive;

    private final List<Vector4f> positions = new ArrayList<>();
    private final List<Vector3f> normals  = new ArrayList<>();
    private final List<Vector3f> tangents  = new ArrayList<>();
    private final List<Vector3f> coTangents  = new ArrayList<>();
    private final List<Color4f> colors  = new ArrayList<>();
    private final List<Vector2f> uv0  = new ArrayList<>();
    private final List<Vector3f> uv03  = new ArrayList<>();
    private final List<Vector4f> uv04  = new ArrayList<>();

    private final List<Vector2f> uv1  = new ArrayList<>();
    private final List<Vector3f> uv13  = new ArrayList<>();
    private final List<Vector4f> uv14  = new ArrayList<>();

    private final List<Integer> indices = new ArrayList<>();

    private  boolean dirty = false;

    private IGraphicsMesh mesh;

    public void setPrimitive(EPrimitive primitive) {
        this.primitive = primitive;
    }

    public EPrimitive getPrimitive() {
        return primitive;
    }

    public void setPositions (List<Vector4f> positions) {
        this.positions.clear();
        this.positions.addAll(positions);
        this.dirty = true;
    }
    
    public List<Vector4f> getPositions() {
        return new ArrayList<>(this.positions);
    }

    public void setNormals (List<Vector3f> normals) {
        this.normals.clear();
        this.normals.addAll(normals);
        this.dirty = true;
    }

    public List<Vector3f> getNormals() {
        return Collections.unmodifiableList(this.normals);
    }

    public void setTangents (List<Vector3f> tangents) {
        this.tangents.clear();
        this.tangents.addAll(tangents);
        this.dirty = true;
    }

    public List<Vector3f> getTangents() {
        return Collections.unmodifiableList(this.tangents);
    }

    public void setCoTangents (List<Vector3f> coTangents) {
        this.coTangents.clear();
        this.coTangents.addAll(coTangents);
        this.dirty = true;
    }

    public List<Vector3f> getCoTangents() {
        return Collections.unmodifiableList(this.coTangents);
    }


    public void setColors (List<Color4f> colors) {
        this.colors.clear();
        this.colors.addAll(colors);
        this.dirty = true;
    }

    public List<Color4f> getColors() {
        return Collections.unmodifiableList(this.colors);
    }


    public void setUV0 (List<Vector2f> uv) {
        this.uv0.clear();
        this.uv0.addAll(uv);
        this.dirty = true;
    }

    public List<Vector2f> getUV0() {
        return Collections.unmodifiableList(this.uv0);
    }


    public void setUV03 (List<Vector3f> uv) {
        this.uv03.clear();
        this.uv03.addAll(uv);
        this.dirty = true;
    }

    public List<Vector3f> getUV03() {
        return Collections.unmodifiableList(this.uv03);
    }



    public void setUV04 (List<Vector4f> uv) {
        this.uv04.clear();
        this.uv04.addAll(uv);
        this.dirty = true;
    }

    public List<Vector4f> getUV04() {
        return Collections.unmodifiableList(this.uv04);
    }




    public void setUV1 (List<Vector2f> uv) {
        this.uv1.clear();
        this.uv1.addAll(uv);
        this.dirty = true;
    }

    public List<Vector2f> getUV1() {
        return Collections.unmodifiableList(this.uv1);
    }


    public void setUV13 (List<Vector3f> uv) {
        this.uv13.clear();
        this.uv13.addAll(uv);
        this.dirty = true;
    }

    public List<Vector3f> getUV13() {
        return Collections.unmodifiableList(this.uv13);
    }



    public void setUV14 (List<Vector4f> uv) {
        this.uv14.clear();
        this.uv14.addAll(uv);
        this.dirty = true;
    }

    public List<Vector4f> getUV14() {
        return Collections.unmodifiableList(this.uv14);
    }


    public void setIndices (List<Integer> indices) {
        this.indices.clear();
        this.indices.addAll(indices);
        this.dirty = true;
    }

    public List<Integer> getIndices () {
        return Collections.unmodifiableList(this.indices);
    }

    public void render (IGraphics graphics) {
        if (this.mesh == null) {
            this.mesh = graphics.createGraphicsMesh();
            this.dirty = true;
        }
        if (this.dirty) {
            this.mesh.update(graphics, this);
            this.dirty = false;
        }

        this.mesh.render(graphics);
    }
}
