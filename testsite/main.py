from flask import Flask, render_template, request, redirect, url_for, flash

app = Flask(__name__)
app.config['SECRET_KEY'] = 'your_secret_key'

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/submit', methods=['GET', 'POST'])
def submit():
    if request.method == 'POST':
        name = request.form.get('name')
        email = request.form.get('email')
        # Simulate some processing logic that might have bugs
        if not name or '@' not in email:
            flash('Invalid input, please try again.', 'danger')
            return redirect(url_for('submit'))
        flash('Form submitted successfully!', 'success')
        return redirect(url_for('index'))
    return render_template('submit.html')

@app.route('/calculate', methods=['GET', 'POST'])
def calculate():
    danger = None
    if request.method == 'POST':
        try:
            num1 = int(request.form.get('num1'))
            num2 = int(request.form.get('num2'))
            result = num1 / num2  # Potential division by zero bug
            return render_template('calculate.html', result=result)
        except ValueError:
            flash('Invalid numbers entered.', 'danger')
        # except ZeroDivisionError:
        #     flash('Cannot divide by zero.', 'danger')
        return render_template('calculate.html', danger=danger)
    return render_template('calculate.html')

if __name__ == '__main__':
    app.run(debug=True)
