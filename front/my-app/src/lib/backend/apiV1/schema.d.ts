/**
 * This file was auto-generated by openapi-typescript.
 * Do not make direct changes to the file.
 */


export interface paths {
    "/api/v1/surls/{id}": {
      /** 단건조회 */
      get: operations["get"];
      /** 수정 */
      put: operations["modify"];
      /** 삭제 */
      delete: operations["delete"];
    };
    "/api/v1/surls": {
      /** 다건조회 */
      get: operations["getItems"];
      /** 생성 */
      post: operations["add"];
    };
    "/api/v1/members": {
      /** 회원가입 */
      post: operations["join"];
    };
    "/api/v1/members/login": {
      /**
       * 로그인
       * @description 성공하면 accessToken, refreshToken 쿠키가 생성됨
       */
      post: operations["login"];
    };
    "/api/v1/members/me": {
      /**
       * 내 정보
       * @description 현재 로그인한 회원의 정보
       */
      get: operations["getMe"];
    };
    "/api/v1/members/logout": {
      /** 로그아웃 */
      delete: operations["logout"];
    };
  }

  export type webhooks = Record<string, never>;

  export interface components {
    schemas: {
      Empty: Record<string, never>;
      RsDataEmpty: {
        resultCode: string;
        /** Format: int32 */
        statusCode: number;
        msg: string;
        data: components["schemas"]["Empty"];
      };
      SurlModifyReqBody: {
        body: string;
        url: string;
      };
      RsDataSurlModifyRespBody: {
        resultCode: string;
        /** Format: int32 */
        statusCode: number;
        msg: string;
        data: components["schemas"]["SurlModifyRespBody"];
      };
      SurlDto: {
        /** Format: int64 */
        id: number;
        /** Format: date-time */
        createDate: string;
        /** Format: date-time */
        modifyDate: string;
        /** Format: int64 */
        authorId: number;
        authorName: string;
        body: string;
        url: string;
        /** Format: int64 */
        count: number;
      };
      SurlModifyRespBody: {
        item: components["schemas"]["SurlDto"];
      };
      SurlAddReqBody: {
        body: string;
        url: string;
      };
      RsDataSurlAddRespBody: {
        resultCode: string;
        /** Format: int32 */
        statusCode: number;
        msg: string;
        data: components["schemas"]["SurlAddRespBody"];
      };
      SurlAddRespBody: {
        item: components["schemas"]["SurlDto"];
      };
      MemberJoinReqBody: {
        username: string;
        password: string;
        nickname: string;
      };
      MemberDto: {
        /** Format: int64 */
        id: number;
        /** Format: date-time */
        createDate: string;
        /** Format: date-time */
        modifyDate: string;
        username: string;
        nickname: string;
      };
      MemberJoinRespBody: {
        item: components["schemas"]["MemberDto"];
      };
      RsDataMemberJoinRespBody: {
        resultCode: string;
        /** Format: int32 */
        statusCode: number;
        msg: string;
        data: components["schemas"]["MemberJoinRespBody"];
      };
      MemberLoginReqBody: {
        username: string;
        password: string;
      };
      MemberLoginRespBody: {
        item: components["schemas"]["MemberDto"];
      };
      RsDataMemberLoginRespBody: {
        resultCode: string;
        /** Format: int32 */
        statusCode: number;
        msg: string;
        data: components["schemas"]["MemberLoginRespBody"];
      };
      RsDataSurlGetItemsRespBody: {
        resultCode: string;
        /** Format: int32 */
        statusCode: number;
        msg: string;
        data: components["schemas"]["SurlGetItemsRespBody"];
      };
      SurlGetItemsRespBody: {
        items: components["schemas"]["SurlDto"][];
      };
      RsDataSurlGetRespBody: {
        resultCode: string;
        /** Format: int32 */
        statusCode: number;
        msg: string;
        data: components["schemas"]["SurlGetRespBody"];
      };
      SurlGetRespBody: {
        item: components["schemas"]["SurlDto"];
      };
      MemberMeRespBody: {
        item: components["schemas"]["MemberDto"];
      };
      RsDataMemberMeRespBody: {
        resultCode: string;
        /** Format: int32 */
        statusCode: number;
        msg: string;
        data: components["schemas"]["MemberMeRespBody"];
      };
    };
    responses: never;
    parameters: never;
    requestBodies: never;
    headers: never;
    pathItems: never;
  }

  export type $defs = Record<string, never>;

  export type external = Record<string, never>;

  export interface operations {

    /** 단건조회 */
    get: {
      parameters: {
        path: {
          id: number;
        };
      };
      responses: {
        /** @description OK */
        200: {
          content: {
            "application/json": components["schemas"]["RsDataSurlGetRespBody"];
          };
        };
        /** @description Bad Request */
        400: {
          content: {
            "application/json": components["schemas"]["RsDataEmpty"];
          };
        };
      };
    };
    /** 수정 */
    modify: {
      parameters: {
        path: {
          id: number;
        };
      };
      requestBody: {
        content: {
          "application/json": components["schemas"]["SurlModifyReqBody"];
        };
      };
      responses: {
        /** @description OK */
        200: {
          content: {
            "application/json": components["schemas"]["RsDataSurlModifyRespBody"];
          };
        };
        /** @description Bad Request */
        400: {
          content: {
            "application/json": components["schemas"]["RsDataEmpty"];
          };
        };
      };
    };
    /** 삭제 */
    delete: {
      parameters: {
        path: {
          id: number;
        };
      };
      responses: {
        /** @description OK */
        200: {
          content: {
            "application/json": components["schemas"]["RsDataEmpty"];
          };
        };
        /** @description Bad Request */
        400: {
          content: {
            "application/json": components["schemas"]["RsDataEmpty"];
          };
        };
      };
    };
    /** 다건조회 */
    getItems: {
      responses: {
        /** @description OK */
        200: {
          content: {
            "application/json": components["schemas"]["RsDataSurlGetItemsRespBody"];
          };
        };
        /** @description Bad Request */
        400: {
          content: {
            "application/json": components["schemas"]["RsDataEmpty"];
          };
        };
      };
    };
    /** 생성 */
    add: {
      requestBody: {
        content: {
          "application/json": components["schemas"]["SurlAddReqBody"];
        };
      };
      responses: {
        /** @description OK */
        200: {
          content: {
            "application/json": components["schemas"]["RsDataSurlAddRespBody"];
          };
        };
        /** @description Bad Request */
        400: {
          content: {
            "application/json": components["schemas"]["RsDataEmpty"];
          };
        };
      };
    };
    /** 회원가입 */
    join: {
      requestBody: {
        content: {
          "application/json": components["schemas"]["MemberJoinReqBody"];
        };
      };
      responses: {
        /** @description OK */
        200: {
          content: {
            "application/json": components["schemas"]["RsDataMemberJoinRespBody"];
          };
        };
        /** @description Bad Request */
        400: {
          content: {
            "application/json": components["schemas"]["RsDataEmpty"];
          };
        };
      };
    };
    /**
     * 로그인
     * @description 성공하면 accessToken, refreshToken 쿠키가 생성됨
     */
    login: {
      requestBody: {
        content: {
          "application/json": components["schemas"]["MemberLoginReqBody"];
        };
      };
      responses: {
        /** @description OK */
        200: {
          content: {
            "application/json": components["schemas"]["RsDataMemberLoginRespBody"];
          };
        };
        /** @description Bad Request */
        400: {
          content: {
            "application/json": components["schemas"]["RsDataEmpty"];
          };
        };
      };
    };
    /**
     * 내 정보
     * @description 현재 로그인한 회원의 정보
     */
    getMe: {
      responses: {
        /** @description OK */
        200: {
          content: {
            "application/json": components["schemas"]["RsDataMemberMeRespBody"];
          };
        };
        /** @description Bad Request */
        400: {
          content: {
            "application/json": components["schemas"]["RsDataEmpty"];
          };
        };
      };
    };
    /** 로그아웃 */
    logout: {
      responses: {
        /** @description OK */
        200: {
          content: {
            "application/json": components["schemas"]["RsDataEmpty"];
          };
        };
        /** @description Bad Request */
        400: {
          content: {
            "application/json": components["schemas"]["RsDataEmpty"];
          };
        };
      };
    };
  }