import json


# Функция для сохранения заметок в файл
def save_notes(notes):
    with open("./notes.json", "w") as f:
        json.dump(notes, f)

# Функция для загрузки заметок из файла
def load_notes():
    try:
        with open("./notes.json", "r") as f:
            return json.load(f)
    except FileNotFoundError:
        return []

# Главная функция
def main():
    global notes
    notes = load_notes()

    while True:
        print("1. Просмотреть заметки")
        print("2. Добавить заметку")
        print("3. Удалить заметку")
        print("4. Редактировать заметку")
        print("5. Выход\n")

        choice = input("Выберите действие: ")

        if choice == "1":
            if not notes:
                print("Заметок нет\n")
                print(" ")
            else:
                for i, note in enumerate(notes):
                    print(f"{i+1}. {note}")
                print(" ")
        elif choice == "2":
            add_note()
            print(" ")
        elif choice == "3":
            delete_note()
            print(" ")
        elif choice == "4":
            edit_note()
            print(" ")
        elif choice == "5":
            break
        else:
            print("Неверный выбор\n")


# Функция для добавления заметки
def add_note():
    global notes
    note = input("Введите заметку: ")
    notes.append(note)
    save_notes(notes)
    print("Заметка добавлена")


# Функция для редактирования заметки
def edit_note():
    global notes
    note_number = input("Введите номер заметки, которую нужно отредактировать: ")
    if not note_number.isdigit() or int(note_number) >= len(notes) or int(note_number) < 0:
        print("Неверный номер заметки\n")
        return
    new_note = input("Введите новый текст заметки: ")
    notes[int(note_number)-1] = new_note
    save_notes(notes)
    print("Заметка изменена\n")


# Функция для удаления заметки
def delete_note():
    global notes
    note_number = input("Введите номер заметки, которую нужно удалить: ")
    if not note_number.isdigit() or int(note_number) >= len(notes) or int(note_number) < 0:
        print("Неверный номер заметки\n")
        return
    del notes[int(note_number)-1]
    save_notes(notes)
    print("Заметка удалена")


if __name__ == '__main__':
    main()
