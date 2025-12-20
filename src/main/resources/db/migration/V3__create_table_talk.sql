CREATE TABLE tb_talk (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    summary TEXT,
    start_time TIMESTAMP,
    duration_min INTEGER
);

CREATE TABLE tb_talk_speaker (
    talk_id INTEGER NOT NULL,
    speaker_id INTEGER NOT NULL,
    PRIMARY KEY (talk_id, speaker_id),
    CONSTRAINT fk_talk_speaker_talk FOREIGN KEY (talk_id) REFERENCES tb_talk(id),
    CONSTRAINT fk_talk_speaker_speaker FOREIGN KEY (speaker_id) REFERENCES tb_speaker(id)
);

CREATE TABLE tb_talk_track (
    talk_id INTEGER NOT NULL,
    track_id INTEGER NOT NULL,
    PRIMARY KEY (talk_id, track_id),
    CONSTRAINT fk_talk_track_talk FOREIGN KEY (talk_id) REFERENCES tb_talk(id),
    CONSTRAINT fk_talk_track_track FOREIGN KEY (track_id) REFERENCES tb_track(id)
)