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
package com.alipay.remoting.util;

import com.alipay.sofa.common.log.ArrayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangg on 2016/7/14 21:07.
 *
 * @author <a href=mailto:zhanggeng.zg@antfin.com>GengZhang</a>
 */
public class StringUtils {

    public static final String   EMPTY              = "";

    public static final String[] EMPTY_STRING_ARRAY = new String[0];

    // Empty checks
    //-----------------------------------------------------------------------
    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence cs) {
        return !StringUtils.isEmpty(cs);
    }

    public static String toString(Object object, String nullStr) {
        return object == null ? nullStr : (object.getClass().isArray() ? ArrayUtil.toString(object)
            : object.toString());
    }

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(CharSequence cs) {
        return !StringUtils.isBlank(cs);
    }

    // Splitting
    //-----------------------------------------------------------------------

    public static String[] split(final String str, final char separatorChar) {
        return splitWorker(str, separatorChar, false);
    }

    private static String[] splitWorker(final String str, final char separatorChar,
                                        final boolean preserveAllTokens) {
        // Performance tuned for 2.0 (JDK1.4)

        if (str == null) {
            return null;
        }
        final int len = str.length();
        if (len == 0) {
            return EMPTY_STRING_ARRAY;
        }
        final List<String> list = new ArrayList<String>();
        int i = 0, start = 0;
        boolean match = false;
        boolean lastMatch = false;
        while (i < len) {
            if (str.charAt(i) == separatorChar) {
                if (match || preserveAllTokens) {
                    list.add(str.substring(start, i));
                    match = false;
                    lastMatch = true;
                }
                start = ++i;
                continue;
            }
            lastMatch = false;
            match = true;
            i++;
        }
        if (match || preserveAllTokens && lastMatch) {
            list.add(str.substring(start, i));
        }
        return list.toArray(new String[list.size()]);
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        } else {
            int sz = str.length();

            for (int i = 0; i < sz; ++i) {
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    public static String trimToEmpty(String str) {
        return trimToEmpty(str, (String) null);
    }

    public static String trim(String str) {
        return trim(str, (String) null, 0);
    }

    public static String trim(String str, String stripChars) {
        return trim(str, stripChars, 0);
    }

    public static String trimStart(String str) {
        return trim(str, (String) null, -1);
    }

    public static String trimStart(String str, String stripChars) {
        return trim(str, stripChars, -1);
    }

    public static String trimEnd(String str) {
        return trim(str, (String) null, 1);
    }

    public static String trimEnd(String str, String stripChars) {
        return trim(str, stripChars, 1);
    }

    private static String trim(String str, String stripChars, int mode) {
        if (str == null) {
            return null;
        } else {
            int length = str.length();
            int start = 0;
            int end = length;
            if (mode <= 0) {
                if (stripChars == null) {
                    while (start < end && Character.isWhitespace(str.charAt(start))) {
                        ++start;
                    }
                } else {
                    if (stripChars.length() == 0) {
                        return str;
                    }

                    while (start < end && stripChars.indexOf(str.charAt(start)) != -1) {
                        ++start;
                    }
                }
            }

            if (mode >= 0) {
                if (stripChars == null) {
                    while (start < end && Character.isWhitespace(str.charAt(end - 1))) {
                        --end;
                    }
                } else {
                    if (stripChars.length() == 0) {
                        return str;
                    }

                    while (start < end && stripChars.indexOf(str.charAt(end - 1)) != -1) {
                        --end;
                    }
                }
            }

            return start <= 0 && end >= length ? str : str.substring(start, end);
        }
    }

    public static boolean contains(String str, char searchChar) {
        return str != null && str.length() != 0 ? str.indexOf(searchChar) >= 0 : false;
    }

    public static boolean contains(String str, String searchStr) {
        return str != null && searchStr != null ? str.indexOf(searchStr) >= 0 : false;
    }

    public static String trimToEmpty(String str, String stripChars) {
        String result = trim(str, stripChars);
        return result == null ? "" : result;
    }

    public static boolean equals(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }
}
