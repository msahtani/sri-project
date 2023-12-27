import React from 'react'



import '/Users/rimmazzine/react-sriproj/src/components/login.css'

export default function login() {
    return (
        <div className="text-center m-5-auto">
            <h2>Sign in to us</h2>
            <form action="/home">
                <p>
                    <label>Username or email address      </label>
                    <input type="text" name="first_name" required />
                </p>
                <p>
                    <label>Password        </label>
                    
                    <label className="right-label">Forget password?</label>
                    <input type="password" name="password" required />
                </p>
                
                <p>
                    <button id="sub_btn" type="submit">Login</button>
                </p>
            </form>
            <footer>
                <p>First time? .</p>
                
            </footer>
        </div>
    )
}