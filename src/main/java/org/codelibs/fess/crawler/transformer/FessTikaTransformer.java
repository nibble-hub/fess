/*
 * Copyright 2012-2015 CodeLibs Project and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.codelibs.fess.crawler.transformer;

import javax.annotation.PostConstruct;

import org.codelibs.fess.crawler.entity.ResponseData;
import org.codelibs.fess.crawler.extractor.Extractor;
import org.codelibs.fess.exception.FessSystemException;
import org.codelibs.fess.mylasta.direction.FessConfig;
import org.codelibs.fess.util.ComponentUtil;
import org.lastaflute.di.core.SingletonLaContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FessTikaTransformer extends AbstractFessFileTransformer {
    private static final Logger logger = LoggerFactory.getLogger(FessTikaTransformer.class);

    @PostConstruct
    public void init() {
        fessConfig = ComponentUtil.getFessConfig();
    }

    @Override
    public FessConfig getFessConfig() {
        return fessConfig;
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

    @Override
    protected Extractor getExtractor(final ResponseData responseData) {
        final Extractor extractor = SingletonLaContainer.getComponent("tikaExtractor");
        if (extractor == null) {
            throw new FessSystemException("Could not find tikaExtractor.");
        }
        return extractor;
    }
}
