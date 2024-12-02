<script lang="ts">
	async function submitLoginForm(this: HTMLFormElement) {
		const form: HTMLFormElement = this;

		form.username.value = form.username.value.trim();
		if (form.username.value.length === 0) {
			alert('username 입력해');
			form.username.focus();
			return;
		}
		form.password.value = form.password.value.trim();
		if (form.password.value.length === 0) {
			alert('password 입력해');
			form.password.focus();
			return;
		}
		const rs = await fetch('http://localhost:8070/api/v1/members/login', {
			method: 'POST',
			//credentials: 'include', : 타 도메인간의 쿠키 통신이 가능하도록
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({
				username: form.username.value,
				password: form.password.value
			})
		}).then((res) => res.json());
		console.log(rs);
	}
</script>

<form on:submit|preventDefault={submitLoginForm}>
    <div>
        <span>
            username
        </span>
        <label>
            <input type="text" name="username" placeholder="username 써">
        </label>
    </div>
    <div>
        <span>
            password
        </span>
        <label>
            <input type="text" name="password" placeholder="password 써">
        </label>
    </div>
    <div>
        <span>
            login
        </span>
        <label>
            <input type="submit" value="LOGIN">
        </label>
    </div>
</form>