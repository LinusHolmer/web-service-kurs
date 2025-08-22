declare namespace NodeJS {
  interface ProcessEnv {
    SECRET: string | undefined;
    DB_CONNECTION_STRING: string | undefined;
    DB_NAME: string | undefined;
    PORT: string | undefined;
  }
}
