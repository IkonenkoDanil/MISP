from allauth.account.adapter import DefaultAccountAdapter


class CustomAccountAdapter(DefaultAccountAdapter):
    def save_user(self, request, user, form, commit=True):
        data = form.cleaned_data
        if 'name' in data:
            user.name = data['name']
        return super(CustomAccountAdapter, self).save_user(request, user, form, commit)
