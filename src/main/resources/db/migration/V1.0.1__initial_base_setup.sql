-- Global Variables -----------------------------------------------------
SET client_encoding = 'UTF8';

-- Table player Definition ----------------------------------------------
CREATE TABLE player (
  id uuid PRIMARY KEY,
  minecraft_id uuid,
  username character varying(255)
);

-- Table player_connection_history Definition ---------------------------
CREATE TABLE player_connection_history (
  id bigint PRIMARY KEY,
  ip character varying(255),
  join_at timestamp(6) without time zone,
  leave_at timestamp(6) without time zone,
  player_id uuid REFERENCES player(id)
);
