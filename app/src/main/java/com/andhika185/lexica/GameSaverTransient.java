package com.andhika185.lexica;

import android.os.Bundle;

import com.andhika185.lexica.db.GameMode;
import com.andhika185.lexica.game.Board;
import com.andhika185.lexica.game.Game;

import java.util.Date;

public class GameSaverTransient extends GameSaver {

    private final Bundle bundle;

    public GameSaverTransient(Bundle bundle) {
        this.bundle = bundle;
    }

    @Override
    public boolean hasSavedGame() {
        return bundle.getBoolean(ACTIVE_GAME, false);
    }

    @Override
    public int readWordCount() {
        return bundle.getInt(WORD_COUNT, DEFAULT_WORD_COUNT);
    }

    @Override
    public String[] readWords() {
        return safeSplit(bundle.getString(WORDS));
    }

    @Override
    public GameMode readGameMode() {
        return bundle.getParcelable(GAME_MODE);
    }

    @Override
    public int readTimeRemaining() {
        return bundle.getInt(TIME_REMAINING, DEFAULT_TIME_REMAINING);
    }

    @Override
    public String[] readGameBoard() {
        return safeSplit(bundle.getString(GAME_BOARD));
    }

    @Override
    public int readBoardSize() {
        return bundle.getInt(BOARD_SIZE, DEFAULT_BOARD_SIZE);
    }

    @Override
    public Game.GameStatus readStatus() {
        String status = bundle.getString(STATUS);
        return status == null ? null : Game.GameStatus.valueOf(status);
    }

    @Override
    public Date readStart() {
        long start = bundle.getLong(START, 0);
        return start == 0 ? null : new Date(start);
    }

    @Override
    public void save(Board board, int timeRemaining, GameMode gameMode, String wordListToString, int wordCount, Date start, Game.GameStatus status) {
        bundle.putInt(GameSaver.BOARD_SIZE, board.getSize());

        bundle.putString(GameSaver.GAME_BOARD, board.toString());
        bundle.putInt(GameSaver.TIME_REMAINING, timeRemaining);
        bundle.putParcelable(GameSaver.GAME_MODE, gameMode);
        bundle.putString(GameSaver.WORDS, wordListToString);
        bundle.putInt(GameSaver.WORD_COUNT, wordCount);
        bundle.putLong(GameSaver.START, start == null ? 0 : start.getTime());
        bundle.putString(GameSaver.STATUS, status.toString());

        bundle.putBoolean(GameSaver.ACTIVE_GAME, true);
    }
}