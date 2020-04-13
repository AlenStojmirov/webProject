import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import './Navbar.css';

class Navbar extends Component {
    render() {
        return (
            <header>
                <nav className="navbar navbar-expand-lg navbar-dark">
                    <a className="navbar-brand" href="../Logo">Logo</a>
                    <button className="navbar-toggler" type="button" data-toggle="collapse"
                            data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false"
                            aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>

                    <div className="collapse navbar-collapse" id="navbarTogglerDemo02">
                        <ul className="navbar-nav mr-auto mt-2 mt-lg-0 m-auto">
                            <li className="nav-item">
                                <a className="nav-link" href="../users">Customers</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="../bookmarks">Bookmarks</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="../leagues">Leagues</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="../matches">Matches</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="../clubs">Clube</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="../sports">Sport</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="../Nullpicks">Regulate Tips</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="../tips/new">Create Tips</a>
                            </li>
                        </ul>
                        <form className="form-inline my-2 my-lg-0">
                            <a href="/login">
                                <button className="btn btn-outline-warning my-2 my-sm-0" type="submit">Logout</button>
                            </a>
                        </form>
                    </div>
                </nav>
            </header>
        );
    }
}

export default Navbar;