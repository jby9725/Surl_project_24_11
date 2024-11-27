package com.koreait.surl_project_11.standard.util;

import com.koreait.surl_project_11.global.app.AppConfig;
import lombok.SneakyThrows;

public class Ut {
    // 접근할 때 Ut.str.isBlank()
    public static class str {
        public static boolean isBlank(String str) {
            return str == null || str.isBlank();
        }

        // 반대 기능 (습관적으로 작성)
        // (비슷한 이름으로 같은 기능) public static boolean hasLength(String str) {}
        public static boolean isNotBlank(String str) {
            return !isBlank(str);
        }
    }

    public static class json {
        @SneakyThrows
        public static String toString(Object obj) {
            return AppConfig.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        }
    }
}
