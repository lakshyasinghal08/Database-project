from flask import Flask, request, jsonify
from flask_cors import CORS
import pyodbc

app = Flask(__name__)
CORS(app)

# 🔹 Database Connection
def get_db():
    conn = pyodbc.connect(
        "DRIVER={ODBC Driver 17 for SQL Server};"
        "SERVER=localhost\\MSSQLSERVER03;"
        "DATABASE=StudentDB;"
        "Trusted_Connection=yes;"
    )
    return conn


# 🔹 Helper function to calculate total, percentage, status
def calculate_result(sub1, sub2, sub3, sub4, sub5):
    total = sub1 + sub2 + sub3 + sub4 + sub5
    percentage = round((total / 500) * 100, 2)

    # Pass only if all subjects >= 40
    if sub1 >= 40 and sub2 >= 40 and sub3 >= 40 and sub4 >= 40 and sub5 >= 40:
        status = "Pass"
    else:
        status = "Fail"

    return total, percentage, status


# 🔹 GET all students
@app.route('/students', methods=['GET'])
def get_students():
    try:
        conn = get_db()
        cursor = conn.cursor()

        cursor.execute("SELECT * FROM students_data")
        rows = cursor.fetchall()

        students = []
        for row in rows:
            students.append({
                "id": row[0],
                "name": row[1],
                "age": row[2],
                "sub1": row[3],
                "sub2": row[4],
                "sub3": row[5],
                "sub4": row[6],
                "sub5": row[7],
                "total": row[8],
                "percentage": row[9],
                "status": row[10]
            })

        conn.close()
        return jsonify(students)

    except Exception as e:
        print("ERROR (GET):", e)
        return jsonify({"error": str(e)})


# 🔹 ADD student
@app.route('/students', methods=['POST'])
def add_student():
    try:
        data = request.json
        print("DATA RECEIVED:", data)

        name = data.get('name')
        age = int(data.get('age'))

        sub1 = int(data.get('sub1'))
        sub2 = int(data.get('sub2'))
        sub3 = int(data.get('sub3'))
        sub4 = int(data.get('sub4'))
        sub5 = int(data.get('sub5'))

        # Validation
        marks_list = [sub1, sub2, sub3, sub4, sub5]
        if not name or age <= 0 or any(m < 0 or m > 100 for m in marks_list):
            return jsonify({"error": "Invalid input data"})

        total, percentage, status = calculate_result(sub1, sub2, sub3, sub4, sub5)

        conn = get_db()
        cursor = conn.cursor()

        cursor.execute(
            """
            INSERT INTO students_data
            (name, age, sub1, sub2, sub3, sub4, sub5, total, percentage, status)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """,
            (name, age, sub1, sub2, sub3, sub4, sub5, total, percentage, status)
        )

        conn.commit()
        conn.close()

        return jsonify({
            "message": "Student added successfully",
            "total": total,
            "percentage": percentage,
            "status": status
        })

    except Exception as e:
        print("ERROR (POST):", e)
        return jsonify({"error": str(e)})


# 🔹 UPDATE student
@app.route('/students/<int:id>', methods=['PUT'])
def update_student(id):
    try:
        data = request.json

        name = data.get('name')
        age = int(data.get('age'))

        sub1 = int(data.get('sub1'))
        sub2 = int(data.get('sub2'))
        sub3 = int(data.get('sub3'))
        sub4 = int(data.get('sub4'))
        sub5 = int(data.get('sub5'))

        # Validation
        marks_list = [sub1, sub2, sub3, sub4, sub5]
        if not name or age <= 0 or any(m < 0 or m > 100 for m in marks_list):
            return jsonify({"error": "Invalid input data"})

        total, percentage, status = calculate_result(sub1, sub2, sub3, sub4, sub5)

        conn = get_db()
        cursor = conn.cursor()

        cursor.execute(
            """
            UPDATE students_data
            SET name=?, age=?, sub1=?, sub2=?, sub3=?, sub4=?, sub5=?, total=?, percentage=?, status=?
            WHERE id=?
            """,
            (name, age, sub1, sub2, sub3, sub4, sub5, total, percentage, status, id)
        )

        conn.commit()
        conn.close()

        return jsonify({
            "message": "Student updated successfully",
            "total": total,
            "percentage": percentage,
            "status": status
        })

    except Exception as e:
        print("ERROR (UPDATE):", e)
        return jsonify({"error": str(e)})


# 🔹 DELETE student
@app.route('/students/<int:id>', methods=['DELETE'])
def delete_student(id):
    try:
        conn = get_db()
        cursor = conn.cursor()

        cursor.execute("DELETE FROM students_data WHERE id=?", (id,))
        conn.commit()
        conn.close()

        return jsonify({"message": "Student deleted successfully"})

    except Exception as e:
        print("ERROR (DELETE):", e)
        return jsonify({"error": str(e)})


# 🔹 Run server
if __name__ == "__main__":
    app.run(debug=True)