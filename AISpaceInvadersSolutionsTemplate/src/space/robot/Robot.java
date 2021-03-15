package space.robot;

import java.util.ArrayList;
import space.Objects;
import space.Alien;
import space.Player;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Huseyin Ozkan 031790084
 */
public class Robot {

    public int globalHamle;
    public static int hStatus;

    //Variable
    private ArrayList<Alien> _aliens;
    private Player _player;
    private String alienDirection = "";
    private boolean actSkip = true;
    private final int alienWidth = 18;
    private final int goToRight = 3;
    private final int goToLeft = -3;
    private final int goToRightFast = 6;
    private final int goToLeftFast = -6;
    private int alienMostLeft;
    private int alienMostRight;
    private int alienOldMostLeft = -1;

    public void act(Objects board) {

        if (board.aliens.isEmpty()) {
            return;
        }

        VariableLoad(board);

        if (actSkip == true) {
            actSkip = false;
            return;
        }

        if (!AlienFollow()) {
            Atack();
        }
    }

    private void VariableLoad(Objects board) {
        _aliens = board.aliens;
        _player = (Player) board.player;

        // En Sağdaki, En Soldaki Alien ve En Soldakinin Eski Konumu Bulunur
        int dizi[] = AlienMostLeftAndMostRightToFind();
        alienOldMostLeft = alienMostLeft;
        alienMostLeft = dizi[0];
        alienMostRight = dizi[1];

        // Alienlerin Yönünü Bulur
        alienDirection = AlienDirectionToFind();
    }

    private boolean AlienFollow() {

        int distance;

        if (_player.x > (alienMostRight + alienMostLeft) / 2) { // En sağa odaklanır

            distance = Math.abs(alienMostRight - _player.x);

            switch (alienDirection) {
                case "sag":                                                                         
                    if (_player.x > alienMostRight && _player.x < alienMostRight + alienWidth / 2) {
                        return false;
                    } else if (_player.x <= alienMostRight) {
                        if (distance > alienWidth * 2) {
                            globalHamle = goToRightFast;
                        } else {
                            globalHamle = goToRight;
                        }
                        return true;
                    } else if (_player.x >= alienMostRight + alienWidth / 2) {
                        if (distance > alienWidth * 2) {
                            globalHamle = goToLeftFast;
                        } else {
                            globalHamle = goToLeft;
                        }
                        return true;
                    }
                    break;
                case "sol":                                                                         //
                    if (_player.x > alienMostRight - alienWidth / 2 && _player.x < alienMostRight) {
                        return false;
                    } else if (_player.x <= alienMostRight - alienWidth / 2) {
                        if (distance > alienWidth * 2) {
                            globalHamle = goToRightFast;
                        } else {
                            globalHamle = goToRight;
                        }
                        return true;
                    } else if (_player.x >= alienMostRight) {
                        if (distance > alienWidth * 2) {
                            globalHamle = goToLeftFast;
                        } else {
                            globalHamle = goToLeft;
                        }
                        return true;
                    }
                    break;
            }
            return true;
        } else {// en sola odaklanır

            distance = Math.abs(alienMostLeft - _player.x);

            switch (alienDirection) {
                case "sag":                                                                         //
                    if (_player.x > alienMostLeft && _player.x < alienMostLeft + alienWidth) {
                        return false;
                    } else if (_player.x <= alienMostLeft) {
                        if (distance > 2 * alienWidth) {
                            globalHamle = goToRightFast;
                        } else {
                            globalHamle = goToRight;
                        }
                        return true;
                    } else if (_player.x >= alienMostLeft + alienWidth) {
                        if (distance > 2 * alienWidth) {
                            globalHamle = goToLeftFast;
                        } else {
                            globalHamle = goToLeft;
                        }
                        return true;
                    }
                    break;
                case "sol":                                                                         //
                    if (_player.x > alienMostLeft - alienWidth && _player.x < alienMostLeft) {
                        return false;
                    } else if (_player.x <= alienMostLeft - alienWidth) {
                        if (distance > 2 * alienWidth) {
                            globalHamle = goToRightFast;
                        } else {
                            globalHamle = goToRight;
                        }
                        return true;
                    } else if (_player.x >= alienMostLeft) {
                        if (distance > 2 * alienWidth) {
                            globalHamle = goToLeftFast;
                        } else {
                            globalHamle = goToLeft;
                        }
                        return true;
                    }
                    break;
            }
            return true;
        }
    }

    private int[] AlienMostLeftAndMostRightToFind() {
        int dizi[] = new int[2];
        dizi[0] = _aliens.get(0).x;
        dizi[1] = _aliens.get(0).x;
        for (int i = 0; i < _aliens.size(); i++) {
            if (_aliens.get(i).x < dizi[0]) {
                dizi[0] = _aliens.get(i).x;
            }
            if (_aliens.get(i).x > dizi[1]) {
                dizi[1] = _aliens.get(i).x;
            }
        }
        return dizi;
    }

    private void Atack() {
        globalHamle = 100;
    }

    private String AlienDirectionToFind() {
        if (alienOldMostLeft == -1) {
            return "Belirli Değil";
        } else {
            if (alienOldMostLeft < alienMostLeft) {
                return "sag";
            } else {
                return "sol";
            }
        }
    }
}
