/*
 * Copyright © 2024 <Your Org or Name>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.cdap.wrangler.api.parser;

import org.junit.Assert;
import org.junit.Test;

public class ByteSizeTest {

    @Test
    public void testByteSizeParsing() throws Exception {
        ByteSize size1 = new ByteSize("10B");
        Assert.assertEquals(10L, size1.getBytes());

        ByteSize size2 = new ByteSize("5KB");
        Assert.assertEquals(5 * 1024L, size2.getBytes());

        ByteSize size3 = new ByteSize("2MB");
        Assert.assertEquals(2 * 1024L * 1024L, size3.getBytes());

        ByteSize size4 = new ByteSize("1.5GB");
        Assert.assertEquals((long)(1.5 * 1024 * 1024 * 1024), size4.getBytes());
    }
}

