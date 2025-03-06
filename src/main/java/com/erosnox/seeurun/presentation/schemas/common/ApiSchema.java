package com.erosnox.seeurun.presentation.schemas.common;

import java.util.Map;

public record ApiSchema<T>(T data, Map<String, Link> _links, String message) { }
