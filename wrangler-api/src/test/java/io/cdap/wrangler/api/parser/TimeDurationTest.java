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

public class TimeDurationTest {

    @Test
    public void testTimeDurationParsing() throws Exception {
        TimeDuration duration1 = new TimeDuration("100ms");
        Assert.assertEquals(100L, duration1.getMillis());

        TimeDuration duration2 = new TimeDuration("5s");
        Assert.assertEquals(5000L, duration2.getMillis());

        TimeDuration duration3 = new TimeDuration("2m");
        Assert.assertEquals(2 * 60 * 1000L, duration3.getMillis());

        TimeDuration duration4 = new TimeDuration("1h");
        Assert.assertEquals(1 * 60 * 60 * 1000L, duration4.getMillis());
    }
}

