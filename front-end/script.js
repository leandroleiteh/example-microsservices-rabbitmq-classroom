document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('studentForm');
    const messageDiv = document.getElementById('message');

    form.addEventListener('submit', async function(event) {
        event.preventDefault();

        const formData = new FormData(form);
        const student = {
            name: formData.get('name'),
            email: formData.get('email')
        };

        try {
            const response = await fetch('http://3.82.127.102:8081/createStudenty', {
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
        } catch (error) {
            messageDiv.textContent = 'Erro ao enviar os dados.';
            console.error('Erro ao enviar os dados:', error);
        }
    });
});

const inputText = document.querySelector("#name");

inputText.addEventListener('input', function(eventInput){
   let currentValue = eventInput.target.value;
    

const regex = new RegExp("^[0-9a-zA-Z @._-]+$"); //permite alguns

const self = this;

setTimeout(function(){
    const text = self.value;

    if(!regex.test(text)){
        self.value = text.replace("/[^0-9a-zA-Z @._-]/g, ''"); //remove alguns
    }
}, 500);

})



