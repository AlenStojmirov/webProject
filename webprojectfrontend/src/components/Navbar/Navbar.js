import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import './Navbar.css';
import {Link} from "react-router-dom";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { logout } from "../../actions/securityActions";

class Navbar extends Component {
    logout() {
        this.props.logout();
        window.location.href = "/";
    }

    render() {

        const { validToken, user } = this.props.security;

        const userIsAuthenticated = (
                <ul className="navbar-nav ml-auto">
                    <li className="nav-item">
                        <Link className="nav-link" to="/dashboard">
                            <i className="fas fa-user-circle mr-1" />
                            {user.username}
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link
                            className="nav-link"
                            to="/logout"
                            onClick={this.logout.bind(this)}>
                            Logout
                        </Link>
                    </li>
                </ul>
        );

        const userIsNotAuthenticated = (
                <ul className="navbar-nav ml-auto">
                    <li className="nav-item">
                        <Link className="nav-link" to="/register">
                            Sign Up
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link" to="/login">
                            Login
                        </Link>
                    </li>
                </ul>
        );

        let headerLinks;

        if (validToken && user) {
            headerLinks = userIsAuthenticated;
        } else {
            headerLinks = userIsNotAuthenticated;
        }

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
                            {/*<a href="/login">*/}
                            {/*    <button className="btn btn-outline-warning my-2 my-sm-0" type="submit">Logout</button>*/}
                            {/*</a>*/}
                            {headerLinks}
                        </form>
                    </div>
                </nav>
            </header>
        );
    }
}

Navbar.propTypes = {
    logout: PropTypes.func.isRequired,
    security: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
    security: state.security
});

export default connect(mapStateToProps, { logout })(Navbar);