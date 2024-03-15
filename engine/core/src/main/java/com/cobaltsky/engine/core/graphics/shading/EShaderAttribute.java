package com.cobaltsky.engine.core.graphics.shading;

public enum EShaderAttribute {

    /* Matrices */
    MODEL_MATRIX,
    VIEW_MATRIX,
    PROJECTION_MATRIX,
    MODEL_VIEW_MATRIX,
    VIEW_PROJECTION_MATRIX,
    MODEL_VIW_PROJECTION_MATRIX,
    MODEL_MATRIX_INV,
    VIEW_MATRIX_INV,
    PROJECTION_MATRIX_INV,
    MODEL_VIEW_MATRIX_INV,
    VIEW_PROJECTION_MATRIX_INV,
    MODEL_VIW_PROJECTION_MATRIX_INV,

    RANDOM,

    SHADOW_MAP_VIEW_MATRIX,
    SHADOW_MAP_PROJECTION_MATRIX,
    SHADOW_MAP_VIW_PROJECTION_MATRIX,

    RENDER_LAYER,

    /* Lighting */
    LIGHT_COLOR,
    LIGHT_VECTOR,
    LIGHT_RANGE,
    LIGHT_COUNT,
    LIGHT_CAST_SHADOW,
    LIGHT_SHADOW_MAP,

    /* ShadowMapping */
    POINT_LIGHT_SHADOW_MAP_MAPPING_BIAS,
    POINT_LIGHT_SHADOW_MAP_COLOR,
    POINT_LIGHT_SHADOW_MAP_DEPTH,


    /* Skeleton rendering */
    SKELETON_MATRICES

}
