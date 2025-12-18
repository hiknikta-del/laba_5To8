package main;

import commands.Command;
import commands.ExitCommand;
import commands.BackToMainMenuCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Scanner;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Тести для класу Menu
 */
class MenuTest {

    private Menu menu;
    private Scanner scanner;

    @BeforeEach
    void setUp() {
        scanner = new Scanner("");
        menu = new Menu("Test Menu", scanner);
    }

    @Test
    @DisplayName("Створення меню з назвою")
    void testMenuCreation() {
        assertThatCode(() -> new Menu("Test", scanner)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Додавання команди до меню")
    void testAddCommand() {
        Command mockCommand = mock(Command.class);
        when(mockCommand.getDescription()).thenReturn("Test Command");

        assertThatCode(() -> menu.addCommand(1, mockCommand)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Меню виконує BackToMainMenuCommand і виходить")
    void testMenuExecutesBackCommand() {
        Scanner testScanner = new Scanner("1\n");
        Menu testMenu = new Menu("Test", testScanner);

        Command backCommand = new BackToMainMenuCommand();
        testMenu.addCommand(1, backCommand);

        // run() має завершитися після виконання BackToMainMenuCommand
        assertThatCode(() -> testMenu.run()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Меню обробляє невалідний вибір")
    void testMenuHandlesInvalidChoice() {
        Scanner testScanner = new Scanner("999\n0\n");
        Menu testMenu = new Menu("Test", testScanner);

        Command backCommand = new BackToMainMenuCommand();
        testMenu.addCommand(0, backCommand);

        assertThatCode(() -> testMenu.run()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Меню обробляє нечисловий інпут")
    void testMenuHandlesNonNumericInput() {
        Scanner testScanner = new Scanner("abc\n0\n");
        Menu testMenu = new Menu("Test", testScanner);

        Command backCommand = new BackToMainMenuCommand();
        testMenu.addCommand(0, backCommand);

        assertThatCode(() -> testMenu.run()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Меню виконує звичайну команду")
    void testMenuExecutesNormalCommand() {
        Command mockCommand = mock(Command.class);
        when(mockCommand.getDescription()).thenReturn("Test Command");

        Scanner testScanner = new Scanner("1\n2\n");
        Menu testMenu = new Menu("Test", testScanner);

        testMenu.addCommand(1, mockCommand);
        testMenu.addCommand(2, new BackToMainMenuCommand());

        testMenu.run();

        verify(mockCommand, times(1)).execute();
    }

    @Test
    @DisplayName("Меню з кількома командами")
    void testMenuWithMultipleCommands() {
        Command cmd1 = mock(Command.class);
        Command cmd2 = mock(Command.class);
        Command cmd3 = mock(Command.class);

        when(cmd1.getDescription()).thenReturn("Command 1");
        when(cmd2.getDescription()).thenReturn("Command 2");
        when(cmd3.getDescription()).thenReturn("Command 3");

        assertThatCode(() -> {
            menu.addCommand(1, cmd1);
            menu.addCommand(2, cmd2);
            menu.addCommand(3, cmd3);
        }).doesNotThrowAnyException();
    }
}