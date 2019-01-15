CREATE TABLE IF NOT EXISTS account (
  account_id BIGSERIAL PRIMARY KEY,
  account_name TEXT UNIQUE NOT NULL,
  account_password TEXT NOT NULL,
  account_role TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS account_info (
  account_id BIGSERIAL REFERENCES account(account_id) ON UPDATE CASCADE ON DELETE CASCADE PRIMARY KEY,
  mail_address TEXT NOT NULL,
  real_name TEXT NOT NULL,
  real_name_ruby TEXT NOT NULL
  );

CREATE TABLE IF NOT EXISTS work (
  work_id BIGSERIAL PRIMARY KEY,
  account_id BIGSERIAL REFERENCES account(account_id) ON UPDATE CASCADE  ON DELETE CASCADE,
  work_title TEXT NOT NULL,
  work_date DATE NOT NULL,
  work_type TEXT NOT NULL,
  more_work_type TEXT NOT NULL,
  work_memo TEXT
);

CREATE TABLE IF NOT EXISTS file (
  file_id BIGSERIAL PRIMARY KEY,
  work_id BIGSERIAL REFERENCES work(work_id) ON UPDATE CASCADE ON DELETE CASCADE,
  file_data BYTEA NOT NULL,
  file_name TEXT NOT NULL
);