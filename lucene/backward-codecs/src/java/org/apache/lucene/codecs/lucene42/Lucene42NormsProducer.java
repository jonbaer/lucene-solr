package org.apache.lucene.codecs.lucene42;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.IOException;
import java.util.Collections;

import org.apache.lucene.codecs.NormsProducer;
import org.apache.lucene.index.FieldInfo;
import org.apache.lucene.index.NumericDocValues;
import org.apache.lucene.index.SegmentReadState;
import org.apache.lucene.util.Accountable;

/**
 * Reads 4.2-4.8 norms.
 * Implemented the same as docvalues, but with a different filename.
 * @deprecated Only for reading old segments
 */
@Deprecated
class Lucene42NormsProducer extends NormsProducer {
  private final Lucene42DocValuesProducer impl;
  
  Lucene42NormsProducer(SegmentReadState state, String dataCodec, String dataExtension, String metaCodec, String metaExtension) throws IOException {
    impl = new Lucene42DocValuesProducer(state, dataCodec, dataExtension, metaCodec, metaExtension);
  }

  @Override
  public NumericDocValues getNorms(FieldInfo field) throws IOException {
    return impl.getNumeric(field);
  }

  @Override
  public void checkIntegrity() throws IOException {
    impl.checkIntegrity();
  }

  @Override
  public long ramBytesUsed() {
    return impl.ramBytesUsed();
  }
  
  @Override
  public Iterable<? extends Accountable> getChildResources() {
    return impl.getChildResources();
  }

  @Override
  public void close() throws IOException {
    impl.close();
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "(" + impl + ")";
  }
}
