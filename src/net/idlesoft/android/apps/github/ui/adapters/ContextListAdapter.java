/*
 * Copyright (c) 2012 Eddie Ringle
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of conditions
 * and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions
 * and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package net.idlesoft.android.apps.github.ui.adapters;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.androidquery.AQuery;
import net.idlesoft.android.apps.github.R;
import net.idlesoft.android.apps.github.ui.activities.BaseActivity;
import org.eclipse.egit.github.core.User;

public
class ContextListAdapter extends BaseListAdapter<User>
{
	public static
	class ViewHolder
	{
		ImageView gravatar;
		TextView login;
	}

	public
	ContextListAdapter(BaseActivity context)
	{
		super(context);
	}

	public
	View doGetView(int position, View convertView, ViewGroup parent, boolean dropdown)
	{
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.user_list_item, null);
			holder = new ViewHolder();
			holder.gravatar = (ImageView) convertView.findViewById(R.id.iv_user_gravatar);
			holder.login = (TextView) convertView.findViewById(R.id.tv_user_login);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		final User user = getItem(position);

		final String login = user.getLogin();
		final String type = user.getType();

		final AQuery aq = new AQuery(convertView);
		aq.id(holder.gravatar).image(user.getAvatarUrl(), true, true, 200, R.drawable.gravatar, null, AQuery.FADE_IN_NETWORK, 1.0f);

		holder.login.setTextColor(Color.WHITE);
		holder.login.setText(login);

		return convertView;
	}

	@Override
	public
	View getView(int position, View convertView, ViewGroup parent)
	{
		return doGetView(position, convertView, parent, false);
	}

	@Override
	public
	View getDropDownView(int position, View convertView, ViewGroup parent)
	{
		return doGetView(position, convertView, parent, true);
	}
}
