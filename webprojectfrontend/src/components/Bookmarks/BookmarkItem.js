import React, {Component} from "react";
import {Link} from "react-router-dom";
import PropTypes from "prop-types";
import {connect} from "react-redux";
import {deleteBookmark} from "../../actions/bookmarkActions";

class BookmarkItem extends Component {

    onDeleteClick = id =>{
        this.props.deleteBookmark(id);
    };

    render() {
        const {bookmark} = this.props;
        return (
            <tr>
                <td>{bookmark.name}</td>
                <td>{bookmark.rating}</td>
                <td>
                    <form>
                        <Link to={`/updateBookmark/${bookmark.id}`}>
                            <button className="btn btn-outline-warning m-1"><i className="fa fa-fw fa-edit"></i></button>
                        </Link>
                            <button onClick={this.onDeleteClick.bind(this,bookmark.id)}
                                    className="btn btn-outline-danger"><i className="fa fa-fw fa-trash"></i></button>
                    </form>
                </td>
            </tr>
        );
    }
}

BookmarkItem.protoType={
    deleteBookmark: PropTypes.func.isRequired
};

export default connect(null,{deleteBookmark}) (BookmarkItem);