package com.wcod.webflux.ms_bff.http.response;

import com.wcod.webflux.ms_bff.models.ClientResponse;
import com.wcod.webflux.ms_bff.models.ProductResponse;

import java.util.List;

public record ProductsByClientResponse(
    ClientResponse clientResponse,
    List<ProductResponse> productResponse
) {
}
