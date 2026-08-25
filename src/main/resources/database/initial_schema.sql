CREATE TABLE artists (
    id UUID NOT NULL,
    name VARCHAR(250) NOT NULL,
    country VARCHAR(50),
    genre VARCHAR(100),
    PRIMARY KEY (id)
);

CREATE TABLE stages (
    id UUID NOT NULL,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(250),
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    PRIMARY KEY (id)
);

CREATE TABLE shows (
    id BIGINT NOT NULL AUTO_INCREMENT,
    artist_id UUID NOT NULL,
    stage_id UUID NOT NULL,
    start_datetime DATETIME(6) NOT NULL,
    end_datetime DATETIME(6) NOT NULL,

    PRIMARY KEY (id),

    CONSTRAINT fk_shows_artist
        FOREIGN KEY (artist_id)
        REFERENCES artists(id),

    CONSTRAINT fk_shows_stage
        FOREIGN KEY (stage_id)
        REFERENCES stages(id),

    CONSTRAINT chk_show_dates
        CHECK (end_datetime > start_datetime)
);

CREATE INDEX idx_shows_artist_id
    ON shows (artist_id);

CREATE INDEX idx_shows_stage_id
    ON shows (stage_id);

CREATE INDEX idx_shows_start_datetime
    ON shows (start_datetime);