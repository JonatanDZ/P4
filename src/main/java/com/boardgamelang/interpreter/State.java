package com.boardgamelang.interpreter;

import com.boardgamelang.AST.bexp.BexpNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class State {
    // σ : Var ⇀ Val — variable store (e.g. win, draw, turn, piece, position)
    public final Map<String, Object> sigma = new HashMap<>();

    // β : Pos ⇀ Piece — occupied positions; absence = empty square
    public final Map<Position, String> beta = new HashMap<>();

    // o : Player ⇀ 𝒫(Piece × Id) — ownership; each player owns a set of identified pieces
    public final Map<String, Set<OwnedPiece>> o = new HashMap<>();

    // g : 𝔹 — gamerule expression (AST, evaluated on demand)
    public BexpNode g;

    // δ : (n₁, n₂) — board dimensions
    public Position delta;

    // t : AssertName ⇀ 𝔹 — test environment
    public final Map<String, Boolean> t = new HashMap<>();

    // w : 𝔹 — win condition expression (AST)
    public BexpNode w;

    // η : 𝔹 — draw condition expression (AST)
    public BexpNode eta;

    public record OwnedPiece(String piece, int id) {}
}
