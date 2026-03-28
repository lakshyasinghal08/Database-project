const API = "http://127.0.0.1:5000/students";

let allStudents = [];

// Load students
async function loadStudents() {
    const res = await fetch(API);
    const data = await res.json();

    allStudents = data;
    renderStudents(data);
    updateStats(data);
}

// Render table
function renderStudents(data) {
    let html = "";

    data.forEach(s => {
        html += `
        <tr>
            <td>${s.id}</td>
            <td>${s.name}</td>
            <td>${s.age}</td>
            <td>${s.sub1}</td>
            <td>${s.sub2}</td>
            <td>${s.sub3}</td>
            <td>${s.sub4}</td>
            <td>${s.sub5}</td>
            <td>${s.total}</td>
            <td>${s.percentage}%</td>
            <td class="${s.status === 'Pass' ? 'pass' : 'fail'}">${s.status}</td>
            <td>
                <button class="edit-btn" onclick="editStudent(${s.id}, '${s.name}', ${s.age}, ${s.sub1}, ${s.sub2}, ${s.sub3}, ${s.sub4}, ${s.sub5})">Edit</button>
                <button class="delete-btn" onclick="deleteStudent(${s.id})">Delete</button>
            </td>
        </tr>
        `;
    });

    document.getElementById("studentsTable").innerHTML = html;
}

// Save student
async function saveStudent() {
    const id = document.getElementById("studentId").value;

    const student = {
        name: document.getElementById("name").value,
        age: document.getElementById("age").value,
        sub1: document.getElementById("sub1").value,
        sub2: document.getElementById("sub2").value,
        sub3: document.getElementById("sub3").value,
        sub4: document.getElementById("sub4").value,
        sub5: document.getElementById("sub5").value
    };

    if (id) {
        await fetch(`${API}/${id}`, {
            method: "PUT",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(student)
        });
    } else {
        await fetch(API, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(student)
        });
    }

    resetForm();
    loadStudents();
}

// Edit
function editStudent(id, name, age, s1, s2, s3, s4, s5) {
    document.getElementById("studentId").value = id;
    document.getElementById("name").value = name;
    document.getElementById("age").value = age;
    document.getElementById("sub1").value = s1;
    document.getElementById("sub2").value = s2;
    document.getElementById("sub3").value = s3;
    document.getElementById("sub4").value = s4;
    document.getElementById("sub5").value = s5;

    document.getElementById("cancelBtn").style.display = "inline-block";
}

// Reset
function resetForm() {
    document.getElementById("studentId").value = "";
    document.querySelectorAll("input").forEach(i => i.value = "");
    document.getElementById("cancelBtn").style.display = "none";
}

// Delete
async function deleteStudent(id) {
    if (!confirm("Delete student?")) return;

    await fetch(`${API}/${id}`, { method: "DELETE" });
    loadStudents();
}

// Search
function filterStudents() {
    const search = document.getElementById("search").value.toLowerCase();
    const filtered = allStudents.filter(s => s.name.toLowerCase().includes(search));
    renderStudents(filtered);
    updateStats(filtered);
}

// Stats
function updateStats(data) {
    const total = data.length;
    const avg = total ? (data.reduce((a, b) => a + b.percentage, 0) / total).toFixed(2) : 0;
    const high = total ? Math.max(...data.map(s => s.percentage)) : 0;

    document.getElementById("totalStudents").innerText = total;
    document.getElementById("avgPercentage").innerText = avg + "%";
    document.getElementById("highestPercentage").innerText = high + "%";
}

loadStudents();