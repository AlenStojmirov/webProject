import React, {Component} from "react";
import Navbar from "../Navbar/Navbar";
import {Link} from "react-router-dom";
import { connect } from "react-redux";
import {getBookmarks} from "../../actions/bookmarkActions";
import PropTypes from "prop-types";
import BookmarkItem from "./BookmarkItem";

class Bookmarks extends Component {

    componentDidMount() {
        this.props.getBookmarks();
    }

    render() {
        const {bookmarks} = this.props.bookmark;

        return (<div>
            <Navbar />

            <div className="container">
                <React.Fragment>
                    <div className="row">
                        <Link to={"/createBookmark"} className="btn btn-outline-warning mx-auto my-5">Create Bookmark</Link>
                    </div>
                </React.Fragment>

                <table className="table table-striped text-center">
                    <thead>
                    <tr>
                        <th scope="col">Bookmark</th>
                        <th scope="col">Rating</th>
                        <th scope="col">#</th>
                    </tr>
                    </thead>
                    <tbody>
                    {bookmarks.map(bookmark =>(
                        <BookmarkItem key={bookmark.id} bookmark={bookmark}/>
                        ))
                    }
                    </tbody>
                </table>

            </div>

        </div>);
    }
}

Bookmarks.propTypes = {
    bookmark: PropTypes.object.isRequired,
    getBookmarks: PropTypes.func.isRequired
};

const mapStateToProps = state => ({
    bookmark:state.bookmark,

});


export default connect(mapStateToProps,{getBookmarks}) (Bookmarks);