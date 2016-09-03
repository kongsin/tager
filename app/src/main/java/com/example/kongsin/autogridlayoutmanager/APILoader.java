package com.example.kongsin.autogridlayoutmanager;

import com.example.kongsin.autogridlayoutmanager.magazine_entities.Magazine;
import com.example.roofit.Caller;
import com.example.roofit.GET;

/**
 * Created by kongsin on 8/27/16.
 */

public interface APILoader {
    @GET(url = "/catalog/1044?c=TH")
    Caller<Magazine> getMagazine();
}
