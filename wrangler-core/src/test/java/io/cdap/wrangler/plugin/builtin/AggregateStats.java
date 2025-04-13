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

package io.cdap.wrangler.plugin.builtin;

import io.cdap.wrangler.TestingRig;
import io.cdap.wrangler.api.Row;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class AggregateStats {

    @Test
    public void testAggregateStats() throws Exception {
        List<Row> rows = Arrays.asList(
            new Row("data_transfer_size", "2MB").add("response_time", "500ms"),
            new Row("data_transfer_size", "3MB").add("response_time", "1500ms")
        );

        String[] recipe = new String[] {
            "aggregate-stats :data_transfer_size :response_time :total_size_mb :total_time_sec"
        };

        List<Row> results = TestingRig.execute(recipe, rows);

        Assert.assertEquals(1, results.size());

        Row result = results.get(0);

        double expectedSizeMb = 5.0; // 2 + 3 MB
        double expectedTimeSec = 2.0; // (500 + 1500)ms = 2000ms = 2s

        Assert.assertEquals(expectedSizeMb, (Double) result.getValue("total_size_mb"), 0.001);
        Assert.assertEquals(expectedTimeSec, (Double) result.getValue("total_time_sec"), 0.001);
    }
}

