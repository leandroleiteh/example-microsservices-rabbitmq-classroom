document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('studentForm');
    const messageDiv = document.getElementById('message');
    const studentTableBody = document.querySelector('table tbody');
    const fetchStudentsBtn = document.getElementById('fetchStudentsBtn'); // Botão para buscar estudantes

    // Função para buscar e renderizar a lista de estudantes
    async function fetchStudents() {
        try {
            const response = await fetch('http://localhost:8080/get-resources-student/get/allStudents');
            if (!response.ok) {
                throw new Error('Erro ao buscar a lista de estudantes.');
            }
            const students = await response.json();
            renderStudents(students);
        } catch (error) {
            console.error('Erro ao buscar estudantes:', error);
        }
    }

    // Função para renderizar a tabela de estudantes
    function renderStudents(students) {
        studentTableBody.innerHTML = ''; // Limpa a tabela antes de preencher
        students.forEach(student => {
            const row = document.createElement('tr');

            const idCell = document.createElement('td');
            idCell.textContent = student.id;
            row.appendChild(idCell);

            const nameCell = document.createElement('td');
            nameCell.textContent = student.name;
            row.appendChild(nameCell);

            const emailCell = document.createElement('td');
            emailCell.textContent = student.email;
            row.appendChild(emailCell);

            studentTableBody.appendChild(row);
        });
    }

    // Adicionar evento de clique ao botão para buscar os estudantes
    fetchStudentsBtn.addEventListener('click', fetchStudents);

    form.addEventListener('submit', async function(event) {
        event.preventDefault();

        const formData = new FormData(form);
        const student = {
            name: formData.get('name'),
            email: formData.get('email')
        };

        try {
            const response = await fetch('http://localhost:8081/createStudent', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(student)
            });

            if (!response.ok) {
                throw new Error('Erro ao enviar os dados.');
            }

            const responseData = await response.json();
            messageDiv.textContent = 'Estudante cadastrado com sucesso!';
            console.log('Resposta da API:', responseData);

            // Atualizar a lista de estudantes após o cadastro
            fetchStudents();
        } catch (error) {
            messageDiv.textContent = 'Erro ao enviar os dados.';
            console.error('Erro ao enviar os dados:', error);
        }
    });
});
